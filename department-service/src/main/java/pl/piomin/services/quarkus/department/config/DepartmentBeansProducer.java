package pl.piomin.services.quarkus.department.config;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepartmentBeansProducer {

//    @ConfigProperty(name = "client.employee.uri", defaultValue = "http://localhost:8080")
//    String employeeUri;
//
//    @Produces
//    @IfBuildProperty(name = "quarkus.consul-discovery.enabled", stringValue = "true")
//    Consul consulClient() {
//        try {
//            return Consul.builder().build();
//        } catch (ConsulException e) {
//            return null;
//        }
//    }

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
