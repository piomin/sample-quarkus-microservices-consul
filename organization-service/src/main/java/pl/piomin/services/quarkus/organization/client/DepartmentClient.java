package pl.piomin.services.quarkus.organization.client;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import pl.piomin.services.quarkus.organization.model.Department;

@Singleton
@Path("/departments")
@RegisterRestClient
public interface DepartmentClient {

    @GET
    @Path("/organization/{organizationId}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Department> findByOrganization(@PathParam("organizationId") Long organizationId);

    @GET
    @Path("/organization/{organizationId}/with-employees")
    @Produces(MediaType.APPLICATION_JSON)
    List<Department> findByOrganizationWithEmployees(@PathParam("organizationId") Long organizationId);

}
