package pl.prasny.component.http.server;

import pl.prasny.api.http.IHttpPort;
import pl.prasny.api.http.IRoute;
import pl.prasny.api.http.exception.server.HttpServerPortException;

import java.util.HashSet;
import java.util.Set;

/**
 * Klasa rodzic dla implementacji PlainJavaHttp
 */
abstract class AbstractHttpPort implements IHttpPort {
    //zbiór reguł routingu zapisanych w obiektach IRoute
    private Set<IRoute> routes = new HashSet<>();
    //port na który serwer ma zostać odpalony
    private String port;

    AbstractHttpPort(String port) throws IllegalArgumentException {
        //walidacja portu
        try {
            Integer portInteger = Integer.parseInt(port);
            if (portInteger < 0 || portInteger > 65535) {
                throw new IllegalArgumentException("not in port range (0-65535)");
            }
        } catch (IllegalArgumentException e) {
            throw new HttpServerPortException("Port string does not contain a parsable integer or integer not in 0-65535 range.");
        }
        this.port = port;
    }

    Set<IRoute> getRoutes() {
        return routes;
    }

    String getPort() {
        return port;
    }
}
