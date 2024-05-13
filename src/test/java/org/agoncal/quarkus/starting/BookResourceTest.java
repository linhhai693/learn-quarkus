package org.agoncal.quarkus.starting;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.HttpHeaders.ACCEPT;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.OK;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
class BookResourceTest {

    @Test
    void testGetAllBooks(){
        given()
                .header(ACCEPT, APPLICATION_JSON)
                .when()
                .get("/api/books")
                .then()
                .statusCode(OK.getStatusCode());
    }

    @Test
    void testGetBookById(){
        given()
                .header(ACCEPT, APPLICATION_JSON)
                .when()
                .get("/api/books/1")
                .then()
                .statusCode(OK.getStatusCode());
    }

}