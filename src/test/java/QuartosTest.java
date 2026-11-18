import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

class QuartosTest {
    @Test
    void deveListarSomenteQuartosDisponiveis() {
        given()
            .baseUri("http://127.0.0.1:3005")
            .queryParam("disponivel", true)
        .when()
            .get("/quartos")
        .then()
            .statusCode(200)
            .body("$", hasSize(1))
            .body("[0].tipo", equalTo("Standard"))
            .body("[0].disponivel", equalTo(true));
    }
}