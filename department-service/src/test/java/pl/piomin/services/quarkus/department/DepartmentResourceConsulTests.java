package pl.piomin.services.quarkus.department;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.consul.CheckOptions;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.Service;
import io.vertx.ext.consul.ServiceList;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.piomin.services.quarkus.department.model.Department;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@QuarkusTest
@QuarkusTestResource(ConsulResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentResourceConsulTests {

    @ConfigProperty(name = "department.name", defaultValue = "")
    private String name;
    @Inject
    ConsulClient consulClient;

    @Test
    @Order(1)
    void add() {
        Department d = new Department();
        d.setOrganizationId(1L);
        d.setName(name);

        given().body(d).contentType(ContentType.JSON)
                .when().post("/departments").then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", is(name));
    }

    @Test
    @Order(2)
    void findAll() {
        when().get("/departments").then()
                .statusCode(200)
                .body("size()", is(4));
    }

    @Test
    @Order(3)
    void checkRegister() throws InterruptedException {
        Thread.sleep(5000);
        Uni<ServiceList> uni = Uni.createFrom().completionStage(() -> consulClient.catalogServices().toCompletionStage());
        List<Service> services = uni.await().atMost(Duration.ofSeconds(3)).getList();
        final long count = services.stream()
                .filter(svc -> svc.getName().equals("department-service")).count();
        assertEquals(1 ,count);
    }
}
