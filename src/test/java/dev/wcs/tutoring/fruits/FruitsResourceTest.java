package dev.wcs.tutoring.fruits;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FruitsResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200)
             .body(containsString("Apple"));
    }

}