package org.agoncal.quarkus.starting;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.core.MediaType;
import org.agoncal.quarkus.starting.dto.UserDTO;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.HttpHeaders.ACCEPT;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.OK;

@QuarkusTest
class TokenControllerTest{
    @Test
    void testGetToken(){
        given().header(ACCEPT, APPLICATION_JSON)
                .when()
                .get("/tokens")
                .then()
                .statusCode(OK.getStatusCode());
    }

    @Test
    void testCreateAToken(){
        given()
                .contentType(ContentType.JSON)
                .body("{\"userName\":\"admin\", \"password\":\"admin\"}")
                .when()
                .post("/tokens")
                .then()
                .statusCode(OK.getStatusCode());
    }


}
