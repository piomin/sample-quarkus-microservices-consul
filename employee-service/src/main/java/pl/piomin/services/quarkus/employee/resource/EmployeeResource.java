package pl.piomin.services.quarkus.employee.resource;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piomin.services.quarkus.employee.model.Employee;
import pl.piomin.services.quarkus.employee.repository.EmployeeRepository;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeResource.class);

	@Inject
	EmployeeRepository repository;

	@POST
	public Employee add(@Valid Employee employee) {
		LOGGER.info("Employee add: {}", employee);
		repository.persist(employee);
		return employee;
	}

	@Path("/{id}")
	@GET
	public Employee findById(@PathParam("id") Long id) {
		LOGGER.info("Employee find: id={}", id);
		return repository.findById(id);
	}

	@GET
	public List<Employee> findAll() {
		LOGGER.info("Employee find");
		return repository.findAll().list();
	}

	@Path("/department/{departmentId}")
	@GET
	public List<Employee> findByDepartment(@PathParam("departmentId") Long departmentId) {
		LOGGER.info("Employee find: departmentId={}", departmentId);
		return repository.findByDepartment(departmentId);
	}

	@Path("/organization/{organizationId}")
	@GET
	public List<Employee> findByOrganization(@PathParam("organizationId") Long organizationId) {
		LOGGER.info("Employee find: organizationId={}", organizationId);
		return repository.findByOrganization(organizationId);
	}

}
