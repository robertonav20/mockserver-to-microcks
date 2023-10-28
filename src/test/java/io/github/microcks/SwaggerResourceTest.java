package io.github.microcks;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWithIgnoringCase;

@QuarkusTest
class SwaggerResourceTest {

    @Test
    void testJsonEndpoint() {
        given().when().get("/swagger/json").then().statusCode(200).body(containsString("openapi"));
    }

    @Test
    void testYamlEndpoint() {
        given().when().get("/swagger/yaml").then().statusCode(200)
                .body(startsWithIgnoringCase("openapi"));
    }

}
