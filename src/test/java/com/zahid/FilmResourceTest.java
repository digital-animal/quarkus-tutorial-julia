package com.zahid;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class FilmResourceTest {

    @Test
    public void test() {
        given()
            .when().get("/film/5" )
            .then()
            .statusCode(200)
            .body(containsString("AFRICAN EGG"));
    }

    @Test
    public void test2() {
        given()
            .when().get("/film/5" )
            .then()
            .statusCode(200)
            .body(containsString("TITANIC"));
    }
}
