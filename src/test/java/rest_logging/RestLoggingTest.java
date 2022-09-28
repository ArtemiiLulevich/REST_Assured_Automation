package rest_logging;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class RestLoggingTest {

    String baseUri = "https://restcountries.com/v3.1";


    @Test
    public void logAllDetails() {

        given()
                .baseUri(baseUri)
                .when()
//                .log().all()
                .get("/alpha/GER")
                .then()
                .log().everything();
//                .log().all();

    }

    @Test
    public void logHeader() {

        given()
                .baseUri(baseUri)
                .when()
//                .log().all()
                .get("/alpha/GER")
                .then()
                .log().headers();

    }

    @Test
    public void logCookiesOrStatus() {

        given()
                .baseUri(baseUri)
                .when()
//                .log().all()
                .get("/alpha/GER")
                .then()
                .log().status();
//                .log().cookies();

    }

    @Test
    public void logIfError() {

        given()
                .baseUri(baseUri)
                .when()
                .get("/alpha/ut")
                .then()
                .log().ifError();

    }

    @Test
    public void logIfErrorValidation() {

        given()
                .baseUri(baseUri)
                .when()
                .get("/alpha/UR ")
                .then()
                .log().ifValidationFails()
                .statusCode(200);

    }

}
