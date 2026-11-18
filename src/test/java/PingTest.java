import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class PingTest {
    @Test
    void deveRetornar201AoConsultarPing() {
        given()
            .baseUri("https://restful-booker.herokuapp.com")
        .when()
            .get("/ping")
        .then()
            .statusCode(201);
    }
  
}
