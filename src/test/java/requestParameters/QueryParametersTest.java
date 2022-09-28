package requestParameters;


import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class QueryParametersTest {

    String baseUri = "https://restcountries.com/v3.1";

    @Test
    public void handlingQueryParameters() {
        given()
                    .baseUri(baseUri)
                    .queryParam("fullText", true)
                .when()
                    .get("/name/India")
                .then()
                    .log().all()
                    .statusCode(200);
    }


}
