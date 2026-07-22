package Gnava.Core.Server;

import Gnava.Core.Server.Endpoints.AbstractServerEndpoint;
import com.sun.net.httpserver.HttpServer;
import org.jspecify.annotations.Nullable;
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

    private @Nullable HttpServer server;

    private boolean isRunning = false;

    public ApiServer(List<AbstractServerEndpoint> endpoints) throws IOException {
        this.endpoints = endpoints;
    }

    public ServerStartResult start() {
        if (isRunning) {
            return ServerStartResult.ALREADY_RUNNING;
        }

        try {
            setupServer();
        } catch (IOException e) {
            LOGGER.error(e.toString());
            return ServerStartResult.INTERNAL_ERROR;
        }

        assert server != null;
        server.start();

        LOGGER.info("Gnava API server started on localhost:{}", PORT);
        LOGGER.debug("Registered endpoints: {}", endpoints);
        isRunning = true;
        return ServerStartResult.STARTED;
    }

    public ServerStopResult stop() {
        if (!isRunning) {
            return ServerStopResult.NOT_STARTED;
        }

        assert server != null;
        server.stop(2);
        isRunning = false;
        server = null;

        return ServerStopResult.STOPPED;
    }

    public boolean isRunning() {
        return isRunning;
    }

    private void setupServer() throws IOException {
        if (server == null) {
            server = HttpServer.create(new InetSocketAddress(PORT), 0);
            for (AbstractServerEndpoint endpoint : endpoints) {
                server.createContext(endpoint.getPath(), endpoint::handle);
            }
            server.setExecutor(null);
        }
    }
}
