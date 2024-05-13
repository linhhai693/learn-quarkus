package org.agoncal.quarkus.starting;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class JpaSecurityJPATest {

    @Test
    void shouldAccessPublicWhenAnonymous() {
        get("/tokens")
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    void shouldNotAccessBookWhenAnonymous() {
        get("/api/books")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);

    }

    @Test
    void shouldNotAccessOrderWhenAnonymous() {
        given()
                .when()
                .get("/api/orders")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }



    @Test
    void shouldAccessBookWhenAdminAuthenticated() {
        given()
                .auth().preemptive().basic("admin", "admin")
                .when()
                .get("/api/books")
                .then()
                .statusCode(HttpStatus.SC_OK);

    }
    @Test
    void shouldAccessOrderWhenSalerAuthenticated() {
        given()
                .auth().preemptive().basic("saler", "saler")
                .when()
                .get("/api/orders")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void shouldNotAccessOrderWhenAdminAuthenticated() {
        given()
                .auth().preemptive().basic("admin", "admin")
                .when()
                .get("/api/orders")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }



    @Test
    void shouldNotAccessBookWhenUserAuthenticated() {
        given()
                .auth().preemptive().basic("saler", "saler")
                .when()
                .get("/api/books")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }
}
