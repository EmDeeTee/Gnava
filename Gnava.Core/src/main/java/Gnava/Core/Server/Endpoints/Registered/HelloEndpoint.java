package Gnava.Core.Server.Endpoints.Registered;

import Gnava.Core.Server.Endpoints.AbstractServerEndpoint;
import com.sun.net.httpserver.HttpExchange;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

@Component
public final class HelloEndpoint extends AbstractServerEndpoint {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public String getPath() {
        return "/hello";
    }

    @Override
    protected JsonNode handle(HttpExchange exchange) {
        ObjectNode json = MAPPER.createObjectNode();
        json.put("hello", "world");
        return json;
    }
}
