package client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public abstract class RestAssuredClient {
    String baseUrl;

    public RestAssuredClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Response get(String path) {
        return RestAssured.given().log().all(true)
                .baseUri(baseUrl)
                .header("Content-Type", "application/json").get(path);
    }

    public Response post(String path, String body) {
        return RestAssured.given().log().all(true)
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .body(body)
                .log().all(true)
                .post(path);
    }

    public Response put(String path, String body) {
        return RestAssured.given().log().all(true)
                .baseUri(baseUrl)
                .contentType("application/json")
                .accept("application/json")
                .body(body)
                .put(path);
    }

    public Response patch(String path, String body) {
        return RestAssured.given().log().all(true)
                .baseUri(baseUrl)
                .contentType("application/json")
                .accept("application/json")
                .body(body)
                .patch(path);
    }

    public Response delete(String path) {
        return RestAssured.given().log().all(true)
                .baseUri(baseUrl)
                .contentType("application/json")
                .delete(path);
    }

}
