package Gnava.Core.Server;

import Gnava.Core.Server.Endpoints.AbstractServerEndpoint;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

@Service
public class ApiServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServer.class);
    private static final int PORT = 8080;
    private final List<AbstractServerEndpoint> endpoints;

    public ApiServer(List<AbstractServerEndpoint> endpoints) {
        this.endpoints = endpoints;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        for (AbstractServerEndpoint endpoint : endpoints) {
            server.createContext(endpoint.getPath(), endpoint::init);
        }

        server.setExecutor(null);
        server.start();

        LOGGER.info("Gnava API server started on localhost:{}", PORT);
        LOGGER.debug("Registered endpoints: {}", endpoints);
    }
}
