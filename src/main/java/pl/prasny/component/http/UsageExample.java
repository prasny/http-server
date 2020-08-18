package pl.prasny.component.http;

import pl.prasny.api.http.IHttpPort;
import pl.prasny.api.http.IHttpPortHandler;
import pl.prasny.api.http.IRequest;
import pl.prasny.api.http.IResponse;
import pl.prasny.api.http.response.Response;
import pl.prasny.component.http.route.Route;
import pl.prasny.component.http.server.PlainJavaHttp;

public class UsageExample {
    public static void main(String[] args) throws Throwable {
        //ENDPOINTS
        String endpoint = "/endpoint/one";
        //HANDLERS
        IHttpPortHandler infoHandler = new InfoHandler();

        //SERVER
        IHttpPort server = new PlainJavaHttp("8888");
        server.startServer();

        //ROUTES for endpoint and handler
        server.addRoute(new Route(endpoint, infoHandler));
    }
}

class InfoHandler implements IHttpPortHandler {
    public IResponse invoke(IRequest request) {
        System.out.println("invoking info router");
        return new Response("InfoHandler");
    }
}
