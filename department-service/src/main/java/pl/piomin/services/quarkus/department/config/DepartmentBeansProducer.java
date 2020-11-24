package pl.piomin.services.quarkus.department.config;

import java.net.URISyntaxException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import org.apache.http.client.utils.URIBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import pl.piomin.services.quarkus.department.client.EmployeeClient;
import pl.piomin.services.quarkus.department.client.LoadBalancedFilter;

@ApplicationScoped
public class DepartmentBeansProducer {

	@ConfigProperty(name = "client.employee.uri")
	String employeeUri;
	@Produces
	Consul consulClient = Consul.builder().build();

	@Produces
	LoadBalancedFilter filter = new LoadBalancedFilter(consulClient);

	@Produces
	EmployeeClient employeeClient() throws URISyntaxException {
		URIBuilder builder = new URIBuilder(employeeUri);
		return RestClientBuilder.newBuilder()
				.baseUri(builder.build())
				.register(filter)
				.build(EmployeeClient.class);
	}

}
