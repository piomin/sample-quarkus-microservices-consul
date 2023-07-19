package pl.piomin.services.quarkus.department.config;

import java.net.URISyntaxException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.ConsulException;
import io.quarkus.arc.profile.UnlessBuildProfile;
import io.quarkus.arc.properties.IfBuildProperty;
import org.apache.http.client.utils.URIBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import pl.piomin.services.quarkus.department.client.EmployeeClient;
import pl.piomin.services.quarkus.department.client.LoadBalancedFilter;

@ApplicationScoped
public class DepartmentBeansProducer {

    @ConfigProperty(name = "client.employee.uri", defaultValue = "http://localhost:8080")
    String employeeUri;

    @Produces
//	@UnlessBuildProfile("test")
    @IfBuildProperty(name = "quarkus.consul-discovery.enabled", stringValue = "true")
    Consul consulClient() {
        try {
            return Consul.builder().build();
        } catch (ConsulException e) {
            return null;
        }
    }

    @Produces
    LoadBalancedFilter filter = new LoadBalancedFilter(consulClient());

    @Produces
    EmployeeClient employeeClient() throws URISyntaxException {
        URIBuilder builder = new URIBuilder(employeeUri);
        return RestClientBuilder.newBuilder()
                .baseUri(builder.build())
                .register(filter)
                .build(EmployeeClient.class);
    }

}
