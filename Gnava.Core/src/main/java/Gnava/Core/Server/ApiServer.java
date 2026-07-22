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
    private boolean isRunning = false;
    private final HttpServer server;

    public ApiServer(List<AbstractServerEndpoint> endpoints) throws IOException {
        this.endpoints = endpoints;
        // NOTE: Maybe don't do this in constructor
        server = HttpServer.create(new InetSocketAddress(PORT), 0);
        for (AbstractServerEndpoint endpoint : endpoints) {
            server.createContext(endpoint.getPath(), endpoint::init);
        }
        server.setExecutor(null);
    }

    public void start() throws IOException {
        server.start();

        LOGGER.info("Gnava API server started on localhost:{}", PORT);
        LOGGER.debug("Registered endpoints: {}", endpoints);
        isRunning = true;
    }

    // TODO: Actually, you can't start it again once stopped
    // https://docs.oracle.com/en/java/javase/21/docs/api/jdk.httpserver/com/sun/net/httpserver/HttpServer.html#stop(int)
    public void stop() {
        server.stop(2);
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
