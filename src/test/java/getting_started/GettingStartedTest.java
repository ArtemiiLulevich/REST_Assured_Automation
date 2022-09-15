package getting_started;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GettingStartedTest {

    String baseUri = "https://restcountries.com/v3.1";

    @Test
    public void simpleGetRequest() {

        given()
                .baseUri(baseUri)
                .when()
                .get("/all")
                .then()
                .statusCode(200);

    }

    @Test
    public void validateJsonResponse() {
        given()
                .baseUri(baseUri)
                .when()
                .get("/alpha/GER")
                .then()
                .statusCode(200)
                .body(
                        "[0].name.common", equalTo("Germany"),
                        "[0].cioc", equalTo("GER"),
                        "[0].altSpellings", hasItem("DE"),
                        "[0].currencies.EUR.symbol", containsString("€")
                );
    }

    @Test
    public void getAllResponseData() {
        Response res = given()
                .baseUri(baseUri)
                .when()
                    .get("/alpha/GER")
                .then()
                    .extract().response();

        System.out.println(res.asString());
    }

    @Test
    public void getResponseDataSingleValueJson() {
        String res = given()
                .baseUri(baseUri)
                .when()
                .get("/alpha/GER")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract().path("[0].name.common");

        System.out.println(res);
    }

    @Test
    public void getResponseDataSingleValueXML() {
        String temp = given()
                .baseUri("https://api.openweathermap.org")
                    .param("q", "Kyiv")
                    .param("appid", "3c23ef865bfe7ecedf64bda6995fb73e")
                    .param("mode", "xml")
                    .param("units", "metric")
                .when()
                    .get("/data/2.5/weather")
                .then()
                    .statusCode(200)
                    .body("current.city.@name", equalTo("Kyiv"),
                        "current.city.country", equalTo("UA"),
                        "current.temperature.@unit", equalTo("celsius")
                    )
                    .extract().path("current.temperature.@value");
        System.out.println(temp);
    }

    @Test
    public void verifyStatusLine() {
        given()
                .baseUri("https://api.printful.com")
                    .when()
                .get("/variant/1")
                    .then()
                .statusLine("HTTP/1.1 404 Not Found");
    }


    @Test
    @Ignore
    public void getWeather() {
        given()
                .baseUri("https://api.openweathermap.org")
//                .param("lat", 47.8671228)
//                .param("lon", 31.0179572)
                    .param("q", "Kyiv")
                    .param("appid", "3c23ef865bfe7ecedf64bda6995fb73e")
                    .param("mode", "xml")
                    .param("units", "metric")
                .when()
                    .get("/data/2.5/weather")
                .then()
                    .statusCode(200)
                    .body("current.city.@name", equalTo("Kyiv"),
                            "current.city.country", equalTo("UA"),
                            "current.temperature.@unit", equalTo("celsius")
                    );

    }
}
