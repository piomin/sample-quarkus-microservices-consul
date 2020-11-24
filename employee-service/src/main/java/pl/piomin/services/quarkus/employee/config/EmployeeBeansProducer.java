package pl.piomin.services.quarkus.employee.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.orbitz.consul.Consul;

@ApplicationScoped
public class EmployeeBeansProducer {

	@Produces
	Consul consulClient = Consul.builder().build();

}
