package pl.prasny.component.http.route;

import pl.prasny.api.http.IHttpPortHandler;
import pl.prasny.api.http.IRoute;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Klasa przechowująca IHttpPortHandlery z metodą wykonawczą invoke oraz przypasane tym handlerom endpointy
 */
public class Route implements IRoute {
    private String endpoint;
    private IHttpPortHandler handler;

    public Route(String endpoint, IHttpPortHandler handler) {
        //safety - when endpoint field is empty or null set "/" as endpoint
        if (!StringUtils.isNotEmpty(endpoint)) {
            this.endpoint = "/";
        } else {
            this.endpoint = endpoint;
        }
        this.handler = handler;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public IHttpPortHandler getHandler() {
        return handler;
    }

    /*
    Equals obejmujący swoją logiką tylko porównywanie endpointów celem użycia
    funkcji contains kolekcji Set tak, by porównywała jedynie endpointy.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(endpoint, route.endpoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endpoint);
    }
}
