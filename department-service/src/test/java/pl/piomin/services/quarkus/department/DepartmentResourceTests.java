package pl.piomin.services.quarkus.department;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.http.ContentType;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.piomin.services.quarkus.department.model.Department;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
@TestProfile(DisableExternalProfile.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentResourceTests {

    @Test
    @Order(1)
    void add() {
        Department d = new Department();
        d.setName("test");
        d.setOrganizationId(1L);

        given().body(d).contentType(ContentType.JSON)
                .when().post("/departments").then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @Order(2)
    void findAll() {
        when().get("/departments").then()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    @Order(2)
    void findById() {
        when().get("/departments/{id}", 1).then()
                .statusCode(200)
                .body("id", is(1));
    }

    @Test
    @Order(2)
    void findByOrganizationId() {
        when().get("/departments/organization/{organizationId}", 1).then()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    @Order(2)
    void findByOrganizationIdNotFound() {
        when().get("/departments/organization/{organizationId}", 100).then()
                .statusCode(200)
                .body("size()", is(0));
    }
}
