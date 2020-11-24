package pl.piomin.services.quarkus.department.resource;

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
import pl.piomin.services.quarkus.department.client.EmployeeClient;
import pl.piomin.services.quarkus.department.model.Department;
import pl.piomin.services.quarkus.department.repository.DepartmentRepository;

@Path("/departments")
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentResource.class);

    @Inject
    DepartmentRepository repository;
    @Inject
    EmployeeClient employeeClient;

    @Path("/")
    @POST
    public Department add(@Valid Department department) {
        LOGGER.info("Department add: {}", department);
        repository.persist(department);
        return department;
    }

    @Path("/{id}")
    @GET
    public Department findById(@PathParam("id") Long id) {
        LOGGER.info("Department find: id={}", id);
        return repository.findById(id);
    }

    @GET
    public List<Department> findAll() {
        LOGGER.info("Department find");
        employeeClient.findByDepartment(1L);
        return repository.findAll().list();
    }

    @Path("/organization/{organizationId}")
    @GET
    public List<Department> findByOrganization(@PathParam("organizationId") Long organizationId) {
        LOGGER.info("Department find: organizationId={}", organizationId);
        return repository.findByOrganization(organizationId);
    }

    @Path("/organization/{organizationId}/with-employees")
    @GET
    public List<Department> findByOrganizationWithEmployees(@PathParam("organizationId") Long organizationId) {
        LOGGER.info("Department find: organizationId={}", organizationId);
        List<Department> departments = repository.findByOrganization(organizationId);
        departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
        return departments;
    }

}
