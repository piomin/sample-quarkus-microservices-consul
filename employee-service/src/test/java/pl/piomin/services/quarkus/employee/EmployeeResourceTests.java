package pl.piomin.services.quarkus.employee;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import pl.piomin.services.quarkus.employee.model.Employee;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class EmployeeResourceTests {

    @Test
    void add() {
        Employee e = Instancio.of(Employee.class)
                .ignore(Select.field(Employee::getId))
                .create();

        given().body(e).contentType(ContentType.JSON)
                .when().post("/employees").then()
                .statusCode(200)
                .body("id", notNullValue());
    }
}
