package Gnava.Core.Server.Endpoints;

import com.sun.net.httpserver.HttpExchange;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class AbstractServerEndpoint {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public abstract String getPath();

    public final void handle(HttpExchange exchange) throws IOException {
        try (exchange) {
            if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.getResponseHeaders().set("Allow", "GET");
                exchange.sendResponseHeaders(405, -1);
                return;
            }

            Object response = buildResponse(exchange);
            byte[] body = MAPPER.writeValueAsBytes(response);

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, body.length);
            exchange.getResponseBody().write(body);
        } finally {
            exchange.close();
        }
    }

    protected abstract Object buildResponse(HttpExchange exchange);
}