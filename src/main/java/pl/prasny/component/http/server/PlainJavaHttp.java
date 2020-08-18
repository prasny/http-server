package pl.prasny.component.http.server;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.prasny.api.http.IHttpPort;
import pl.prasny.api.http.IRoute;
import pl.prasny.api.http.exception.server.*;
import pl.prasny.component.http.router.Router;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;






public class PlainJavaHttp extends AbstractHttpPort implements IHttpPort {
    private HttpServer server;
    final private Logger LOGGER = LoggerFactory.getLogger(PlainJavaHttp.class);

    public PlainJavaHttp(String port) throws Throwable {
        super(port);
        try {
            //create server on port $port
            server = HttpServer.create(new InetSocketAddress(Integer.valueOf(port)), 0);
            //create one, global route definition
            server.createContext("/", new Router(getRoutes()));
            server.setExecutor(null);
            //catch every exception that prevents to start server
        } catch (BindException e) { //throwed by HttpServer.create()
            throw new HttpServerBindException("Server cannot bind to the requested address, or if the server is already bound.");
        } catch (IllegalStateException e) { //throwed by server.setExecutor()
            throw new HttpServerIllegalStateException("Server already started.");
        } catch (IllegalArgumentException e) { //throwed by server.createContext()
            throw new HttpServerIllegalArgumentException("Context path is invalid, or context already exists for this path");
        } catch (NullPointerException e) { //throwed by server.createContext()
            throw new HttpServerNullPointerException("Context path or router is null");
        } catch (IOException e) { //throwed by HttpServer.create() (and other which are children of IOException)
            throw new HttpServerIOException(e.getClass().getName() + " " + e.getMessage());
        } catch (Throwable e) { //catch everything else and print it
            throw new Throwable(e.getClass().getName() + " " + e.getMessage());
        }
    }

    public void addRoute(IRoute route) {
        //check if route or its fields are null and checks if route added
        try {
            if (route == null ||
                    route.getHandler() == null ||
                    route.getEndpoint() == null ||
                    !getRoutes().add(route)) {
                throw new HttpServerRouteAddException("Route already exists or route, handler or endpoint is null");
            }
        } catch (RuntimeException e) {
            throw new HttpServerRouteAddException(e.getClass().getName() + " " + e.getMessage());
        }
    }

    public void removeRoute(IRoute route) {
        //checked if route removed
        try {
            if (!getRoutes().remove(route) || route == null) {
                LOGGER.warn("Cannot find route or route is null.");
            }
        } catch (RuntimeException e) {
            throw new HttpServerRouteRemoveException(e.getClass().getName() + " " + e.getMessage());
        }
    }

    public void startServer() {
        server.start();
    }

    public void stopServer() {
        server.stop(0);
    }
}
