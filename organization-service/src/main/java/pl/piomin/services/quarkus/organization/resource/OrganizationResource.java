package pl.piomin.services.quarkus.organization.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piomin.services.quarkus.organization.client.DepartmentClient;
import pl.piomin.services.quarkus.organization.client.EmployeeClient;
import pl.piomin.services.quarkus.organization.model.Organization;
import pl.piomin.services.quarkus.organization.repository.OrganizationRepository;

@Path("/organizations")
@Produces(MediaType.APPLICATION_JSON)
public class OrganizationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationResource.class);

    @Inject
    OrganizationRepository repository;
    @Inject
    DepartmentClient departmentClient;
    @Inject
    EmployeeClient employeeClient;

    @POST
    public Organization add(@Valid Organization organization) {
        LOGGER.info("Organization add: {}", organization);
        repository.persist(organization);
        return organization;
    }

    @GET
    public List<Organization> findAll() {
        LOGGER.info("Organization find");
        return repository.findAll().list();
    }

    @Path("/{id}")
    @GET
    public Organization findById(@PathParam("id") Long id) {
        LOGGER.info("Organization find: id={}", id);
        return repository.findById(id);
    }

    @Path("/{id}/with-departments")
    @GET
    public Organization findByIdWithDepartments(@PathParam("id") Long id) {
        LOGGER.info("Organization find: id={}", id);
        Organization organization = repository.findById(id);
        organization.setDepartments(departmentClient.findByOrganization(organization.getId()));
        return organization;
    }

    @Path("/{id}/with-departments-and-employees")
    @GET
    public Organization findByIdWithDepartmentsAndEmployees(@PathParam("id") Long id) {
        LOGGER.info("Organization find: id={}", id);
        Organization organization = repository.findById(id);
        organization.setDepartments(departmentClient.findByOrganizationWithEmployees(organization.getId()));
        return organization;
    }

    @Path("/{id}/with-employees")
    @GET
    public Organization findByIdWithEmployees(@PathParam("id") Long id) {
        LOGGER.info("Organization find: id={}", id);
        Organization organization = repository.findById(id);
        organization.setEmployees(employeeClient.findByOrganization(organization.getId()));
        return organization;
    }

}
