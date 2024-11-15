package pl.piomin.services.quarkus.department.config;

import io.quarkus.arc.lookup.LookupIfProperty;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class DepartmentBeanProducer {

    @ConfigProperty(name = "consul.host", defaultValue = "localhost")  String host;
    @ConfigProperty(name = "consul.port", defaultValue = "8500") int port;

    @Produces
    @LookupIfProperty(name = "quarkus.stork.department-service.service-registrar.type", stringValue = "consul")
    public ConsulClient consulClient(Vertx vertx) {
        return ConsulClient.create(vertx, new ConsulClientOptions()
                .setHost(host)
                .setPort(port));
    }

//    @ConfigProperty(name = "client.employee.uri", defaultValue = "http://localhost:8080")
//    String employeeUri;

//    @Produces
//    LoadBalancedFilter filter = new LoadBalancedFilter(consulClient());
//
//    @Produces
//    EmployeeClient employeeClient() throws URISyntaxException {
//        URIBuilder builder = new URIBuilder(employeeUri);
//        return RestClientBuilder.newBuilder()
//                .baseUri(builder.build())
//                .register(filter)
//                .build(EmployeeClient.class);
//    }

}
