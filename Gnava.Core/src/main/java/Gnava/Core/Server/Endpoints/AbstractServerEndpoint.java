package Gnava.Core.Server.Endpoints;

import com.sun.net.httpserver.HttpExchange;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class AbstractServerEndpoint {
    public abstract String getPath();

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public final void init(HttpExchange exchange) throws IOException {
        try (exchange) {
            Object response = handle(exchange);

            byte[] body = MAPPER.writeValueAsBytes(response);

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, body.length);
            exchange.getResponseBody().write(body);
        }
    }

    protected abstract Object handle(HttpExchange exchange);
}