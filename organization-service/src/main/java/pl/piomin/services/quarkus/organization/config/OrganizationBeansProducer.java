package pl.piomin.services.quarkus.organization.config;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrganizationBeansProducer {

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
