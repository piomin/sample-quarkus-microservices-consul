package pl.piomin.services.quarkus.department.resource;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import pl.piomin.services.quarkus.department.client.EmployeeClient;
import pl.piomin.services.quarkus.department.model.Department;
import pl.piomin.services.quarkus.department.repository.DepartmentRepository;

@Path("/departments")
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentResource {

    private Logger logger;
    private DepartmentRepository repository;
    private EmployeeClient employeeClient;

    public DepartmentResource(Logger logger,
                              DepartmentRepository repository,
                              @RestClient EmployeeClient employeeClient) {
        this.logger = logger;
        this.repository = repository;
        this.employeeClient = employeeClient;
    }

    @Path("/")
    @POST
    @Transactional
    public Department add(@Valid Department department) {
        logger.infof("Department add: %s", department);
        repository.persist(department);
        return department;
    }

    @Path("/{id}")
    @GET
    public Department findById(@PathParam("id") Long id) {
        logger.infof("Department find: id=%d", id);
        return repository.findById(id);
    }

    @GET
    public List<Department> findAll() {
        logger.infof("Department find");
        return repository.findAll().list();
    }

    @Path("/organization/{organizationId}")
    @GET
    public List<Department> findByOrganization(@PathParam("organizationId") Long organizationId) {
        logger.infof("Department find: organizationId=%d", organizationId);
        return repository.findByOrganization(organizationId);
    }

    @Path("/organization/{organizationId}/with-employees")
    @GET
    public List<Department> findByOrganizationWithEmployees(@PathParam("organizationId") Long organizationId) {
        logger.infof("Department find with employees: organizationId=%d", organizationId);
        List<Department> departments = repository.findByOrganization(organizationId);
        departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
        return departments;
    }

}
