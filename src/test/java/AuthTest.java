import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

class AuthTest {
    @Test
    void deveGerarTokenComCredenciaisValidas() {
        given()
            .baseUri("https://restful-booker.herokuapp.com")
            .contentType("application/json")
            .body("""
                {
                    "username": "admin",
                    "password": "password123"
                }
                """)
        .when()
            .post("/auth")
        .then()
            .statusCode(200)
            .body("token", not(emptyOrNullString()));
    }
        @Test
    void deveRejeitarCredenciaisInvalidas() {
        given()
            .baseUri("https://restful-booker.herokuapp.com")
            .contentType("application/json")
            .body("""
                {
                    "username": "admin",
                    "password": "senha-invalida"
                }
                """)
        .when()
            .post("/auth")
        .then()
            .statusCode(200)
            .body("reason", equalTo("Bad credentials"))
            .body("token", nullValue());
    }
}