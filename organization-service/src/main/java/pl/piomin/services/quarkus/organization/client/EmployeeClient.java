package pl.piomin.services.quarkus.organization.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import pl.piomin.services.quarkus.organization.model.Employee;

import java.util.List;

@Path("/employees")
@RegisterRestClient(baseUri = "stork://employee-service")
public interface EmployeeClient {

    @GET
    @Path("/organization/{organizationId}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Employee> findByOrganization(@PathParam("organizationId") Long organizationId);

}
