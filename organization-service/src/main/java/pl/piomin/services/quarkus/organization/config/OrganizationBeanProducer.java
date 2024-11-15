package pl.piomin.services.quarkus.organization.config;

import io.quarkus.arc.lookup.LookupIfProperty;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class OrganizationBeanProducer {

    @ConfigProperty(name = "consul.host", defaultValue = "localhost")  String host;
    @ConfigProperty(name = "consul.port", defaultValue = "8500") int port;

    @Produces
    @LookupIfProperty(name = "quarkus.stork.employee-service.service-registrar.type", stringValue = "consul")
    public ConsulClient consulClient(Vertx vertx) {
        return ConsulClient.create(vertx, new ConsulClientOptions()
                .setHost(host)
                .setPort(port));
    }

//    @Produces
//    Consul consulClient = Consul.builder().build();
//
//    @Produces
//    LoadBalancedFilter employeeFilter = new LoadBalancedFilter(consulClient);
//
//    @Produces
//    LoadBalancedFilter departmentFilter = new LoadBalancedFilter(consulClient);
//
//    @Produces
//    public EmployeeClient employeeClient() throws URISyntaxException {
//        URIBuilder builder = new URIBuilder("http://employee");
//        return RestClientBuilder.newBuilder()
//                .baseUri(builder.build())
//                .register(employeeFilter)
//                .build(EmployeeClient.class);
//    }
//
//    @Produces
//    public DepartmentClient departmentClient() throws URISyntaxException {
//        URIBuilder builder = new URIBuilder("http://department");
//        return RestClientBuilder.newBuilder()
//                .baseUri(builder.build())
//                .register(departmentFilter)
//                .build(DepartmentClient.class);
//    }

}
