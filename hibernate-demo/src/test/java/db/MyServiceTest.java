package db;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class MyServiceTest {

    @Test
    public void testListAllEmployees() {
        given()
          .when().get("/employees")
          .then()
             .statusCode(200)
             .body(
			containsString("Employee-1"),
		   	containsString("Employee-2"),
			containsString("Employee-3"),
			containsString("Employee-4")
		  );
    }

}
