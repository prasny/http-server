 Implementacja serwera HTTP IHttpPort oparta na bibliotece java.net
 
 Przykład użycia:
 
 <pre>
 public class Main {
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
 
     public class InfoHandler implements IHttpPortHandler {
          public IResponse invoke(IRequest request) {
              System.out.println("invoking info router");
              return new Response("InfoHandler");
          }
     }
 }
 </pre>
