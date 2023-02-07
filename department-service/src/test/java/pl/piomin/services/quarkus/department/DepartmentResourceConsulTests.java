package pl.piomin.services.quarkus.department;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.piomin.services.quarkus.department.model.Department;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

//@QuarkusTest
//@QuarkusTestResource(ConsulResource.class, rest)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentResourceConsulTests {

//    @ConfigProperty(name = "department.name", defaultValue = "")
//    private String name;

//    @Test
//    @Order(1)
//    void add() {
//        Department d = new Department();
//        d.setOrganizationId(1L);
//        d.setName(name);
//
//        given().body(d).contentType(ContentType.JSON)
//                .when().post("/departments").then()
//                .statusCode(200)
//                .body("id", notNullValue());
//    }

//    @Test
//    @Order(2)
//    void findAll() {
//        when().get("/departments").then()
//                .statusCode(200)
//                .body("size()", is(1));
//    }
}
