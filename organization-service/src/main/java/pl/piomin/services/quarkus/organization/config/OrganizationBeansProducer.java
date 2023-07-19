package pl.piomin.services.quarkus.organization.config;

import java.net.URISyntaxException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import com.orbitz.consul.Consul;
import org.apache.http.client.utils.URIBuilder;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import pl.piomin.services.quarkus.organization.client.DepartmentClient;
import pl.piomin.services.quarkus.organization.client.EmployeeClient;
import pl.piomin.services.quarkus.organization.client.LoadBalancedFilter;

@ApplicationScoped
public class OrganizationBeansProducer {

    @Produces
    Consul consulClient = Consul.builder().build();

    @Produces
    LoadBalancedFilter employeeFilter = new LoadBalancedFilter(consulClient);

    @Produces
    LoadBalancedFilter departmentFilter = new LoadBalancedFilter(consulClient);

    @Produces
    public EmployeeClient employeeClient() throws URISyntaxException {
        URIBuilder builder = new URIBuilder("http://employee");
        return RestClientBuilder.newBuilder()
                .baseUri(builder.build())
                .register(employeeFilter)
                .build(EmployeeClient.class);
    }

    @Produces
    public DepartmentClient departmentClient() throws URISyntaxException {
        URIBuilder builder = new URIBuilder("http://department");
        return RestClientBuilder.newBuilder()
                .baseUri(builder.build())
                .register(departmentFilter)
                .build(DepartmentClient.class);
    }

}
