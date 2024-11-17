package pl.piomin.services.quarkus.employee.resource;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.jboss.logging.Logger;
import pl.piomin.services.quarkus.employee.model.Employee;
import pl.piomin.services.quarkus.employee.repository.EmployeeRepository;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private Logger logger;
    private EmployeeRepository repository;

    public EmployeeResource(Logger logger,
                            EmployeeRepository repository) {
        this.logger = logger;
        this.repository = repository;
    }

    @POST
    @Transactional
    public Employee add(@Valid Employee employee) {
        logger.infof("Employee add: %s", employee);
        repository.persist(employee);
        return employee;
    }

    @Path("/{id}")
    @GET
    public Employee findById(@PathParam("id") Long id) {
        logger.infof("Employee find: id=%s", id);
        return repository.findById(id);
    }

    @GET
    public List<Employee> findAll() {
        logger.infof("Employee find");
        return repository.findAll().list();
    }

    @Path("/department/{departmentId}")
    @GET
    public List<Employee> findByDepartment(@PathParam("departmentId") Long departmentId) {
        logger.infof("Employee find: departmentId=%s", departmentId);
        return repository.findByDepartment(departmentId);
    }

    @Path("/organization/{organizationId}")
    @GET
    public List<Employee> findByOrganization(@PathParam("organizationId") Long organizationId) {
        logger.infof("Employee find: organizationId=%s", organizationId);
        return repository.findByOrganization(organizationId);
    }

}
