package Gnava.Core.Server.Endpoints.Registered;

import Gnava.Core.Server.Endpoints.AbstractServerEndpoint;
import Gnava.Core.Server.Endpoints.Responces.HelloResponse;
import com.sun.net.httpserver.HttpExchange;
import org.springframework.stereotype.Component;

@Component
public final class HelloEndpoint extends AbstractServerEndpoint {
    @Override
    public String getPath() {
        return "/hello";
    }

    @Override
    protected Object buildResponse(HttpExchange exchange) {
        return new HelloResponse(
            "world"
        );
    }
}
