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

    @Test
    public void post_file_request() {

        File file = new File("src/test/resources/create_user.json");

        Response res = given()
                        .baseUri(baseURI)
                        .header("app-id", "6322d04b1f1e5736304063fd")
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
        body.put("email", "yuhosransk@gmail.com");

        Response res = given()
                .baseUri(baseURI)
                .header("app-id", "6322d04b1f1e5736304063fd")
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

        String id = "632c0b360dc48a215267ce7c";

        JSONObject body = new JSONObject();

        body.put("firstName", "Vasia");
        body.put("lastName", "zhopin");

        Response res = given()
                .baseUri(baseURI)
                .header("app-id", "6322d04b1f1e5736304063fd")
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

}
