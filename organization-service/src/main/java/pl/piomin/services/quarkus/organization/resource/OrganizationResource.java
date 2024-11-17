package pl.piomin.services.quarkus.organization.resource;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import pl.piomin.services.quarkus.organization.client.DepartmentClient;
import pl.piomin.services.quarkus.organization.client.EmployeeClient;
import pl.piomin.services.quarkus.organization.model.Organization;
import pl.piomin.services.quarkus.organization.repository.OrganizationRepository;

@Path("/organizations")
@Produces(MediaType.APPLICATION_JSON)
public class OrganizationResource {

    private Logger logger;
    private OrganizationRepository repository;
    private DepartmentClient departmentClient;
    private EmployeeClient employeeClient;

    public OrganizationResource(Logger logger,
                                OrganizationRepository repository,
                                @RestClient DepartmentClient departmentClient,
                                @RestClient EmployeeClient employeeClient) {
        this.logger = logger;
        this.repository = repository;
        this.departmentClient = departmentClient;
        this.employeeClient = employeeClient;
    }

    @POST
    public Organization add(@Valid Organization organization) {
        logger.infof("Organization add: {}", organization);
        repository.persist(organization);
        return organization;
    }

    @GET
    public List<Organization> findAll() {
        logger.info("Organization find");
        return repository.findAll().list();
    }

    @Path("/{id}")
    @GET
    public Organization findById(@PathParam("id") Long id) {
        logger.infof("Organization find: id={}", id);
        return repository.findById(id);
    }

    @Path("/{id}/with-departments")
    @GET
    public Organization findByIdWithDepartments(@PathParam("id") Long id) {
        logger.infof("Organization find with departments: id={}", id);
        Organization organization = repository.findById(id);
        organization.setDepartments(departmentClient.findByOrganization(organization.getId()));
        return organization;
    }

    @Path("/{id}/with-departments-and-employees")
    @GET
    public Organization findByIdWithDepartmentsAndEmployees(@PathParam("id") Long id) {
        logger.infof("Organization find with departments and employees: id={}", id);
        Organization organization = repository.findById(id);
        organization.setDepartments(departmentClient.findByOrganizationWithEmployees(organization.getId()));
        return organization;
    }

    @Path("/{id}/with-employees")
    @GET
    public Organization findByIdWithEmployees(@PathParam("id") Long id) {
        logger.infof("Organization find with employees: id={}", id);
        Organization organization = repository.findById(id);
        organization.setEmployees(employeeClient.findByOrganization(organization.getId()));
        return organization;
    }

}
