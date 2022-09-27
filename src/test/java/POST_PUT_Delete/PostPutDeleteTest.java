package POST_PUT_Delete;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostPutDeleteTest {

    String baseURI = "https://dummyapi.io/data/v1";
    String appID = "6332f7b9b8558b8a90a7f080";


    @Test
    public void post_file_request() {

        File file = new File("src/test/resources/create_user.json");

        Response res = given()
                        .baseUri(baseURI)
                        .header("app-id", appID)
                        .contentType(ContentType.JSON)
                        .body(file)
                    .when()
                        .post("/user/create")
                    .then()
                        .statusCode(200)
                        .extract().response();

        System.out.println(res.asString());
    }

    @Test
    public void post_Json_Object_request() {

        JSONObject body = new JSONObject();

        body.put("firstName", "Vasia");
        body.put("lastName", "Pupkin");
        body.put("email", "puhosransk@gmail.com");

        Response res = given()
                .baseUri(baseURI)
                .header("app-id", appID)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post("/user/create")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(res.asString());
    }

    @Test
    public void put_Json_Object_request() {

        String id = "6332f884406b0b778ac5ead3";

        JSONObject body = new JSONObject();

        body.put("firstName", "Vasia");
        body.put("lastName", "zhopin");

        Response res = given()
                .baseUri(baseURI)
                .header("app-id", appID)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .put("/user/" + id)
                .then()
                .statusCode(200)
                .body("lastName", equalTo("zhopin"))
                .extract().response();

        System.out.println(res.asString());
    }

    @Test
    public void delete_request() {

        String id = "6332f884406b0b778ac5ead3";


        Response res = given()
                .baseUri(baseURI)
                .header("app-id", appID)
                .when()
                .delete("/user/" + id)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(res.asString());
    }

    @Test
    public void getRequest() {
        String id = "6332f884406b0b778ac5ead3";

        given()
                .baseUri(baseURI)
                .header("app-id", appID)
                .when()
                .get("/user/" + id)
                .then()
                .statusCode(404);
    }

}
