import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
class BookingTest {

    @Test
    void deveRetornar201AoConsultarPing() {
        given()
            .baseUri("https://restful-booker.herokuapp.com")
        .when()
            .get("/booking")
        .then()
            .statusCode(200)
.body("$", not(empty()))
.body("bookingid", everyItem(instanceOf(Number.class)));
  
}
    @Test
    void deveCriarReserva() {
      int bookingId = given()
  
            .baseUri("https://restful-booker.herokuapp.com")
            .contentType("application/json")
            .accept("application/json")
            .body("""
                {
                    "firstname": "Mariana",
                    "lastname": "Teste QA",
                    "totalprice": 180,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2027-03-20",
                        "checkout": "2027-03-23"
                    },
                    "additionalneeds": "Breakfast"
                }
                """)
        .when()
            .post("/booking")
        .then()
            .statusCode(200)
            .body("bookingid", instanceOf(Number.class))
            .body("booking.totalprice", equalTo(180))

            .extract().path("bookingid");
                       

        given()
            .baseUri("https://restful-booker.herokuapp.com")
            .accept("application/json")
        .when()
            .get("/booking/{id}", bookingId)
        .then()
            .statusCode(200)
            .body("firstname", equalTo("Mariana"))
             .body("totalprice", equalTo(180));

      

        given()
            .baseUri("https://restful-booker.herokuapp.com")
            .contentType("application/json")
            .accept("application/json")
            .auth().preemptive().basic("admin", "password123")
            .body("""
                {"totalprice": 220}
                """)
        .when()
            .patch("/booking/{id}", bookingId)
        .then()
            .statusCode(200)
            .body("totalprice", equalTo(220))
            .body("firstname", equalTo("Mariana"));
    
            given()
            .baseUri("https://restful-booker.herokuapp.com")
            .accept("application/json")
        .when()
            .get("/booking/{id}", bookingId)
        .then()
            .statusCode(200)
            .body("totalprice", equalTo(220));
                    given()
            .baseUri("https://restful-booker.herokuapp.com")
            .contentType("application/json")
            .accept("application/json")
            .auth().preemptive().basic("admin", "password123")
            .body("""
                {
                    "firstname": "Mariana",
                    "lastname": "Teste QA",
                    "totalprice": 250,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2027-03-20",
                        "checkout": "2027-03-23"
                    },
                    "additionalneeds": "Breakfast"
                }
                """)
        .when()
            .put("/booking/{id}", bookingId)
        .then()
            .statusCode(200)
            .body("totalprice", equalTo(250));
                    given()
            .baseUri("https://restful-booker.herokuapp.com")
            .auth().preemptive().basic("admin", "password123")
        .when()
            .delete("/booking/{id}", bookingId)
        .then()
            .statusCode(201);

        given()
            .baseUri("https://restful-booker.herokuapp.com")
        .when()
            .get("/booking/{id}", bookingId)
        .then()
            .statusCode(404);
}
}