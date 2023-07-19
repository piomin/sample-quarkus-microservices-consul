package pl.piomin.services.quarkus.department.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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
    @Transactional
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
