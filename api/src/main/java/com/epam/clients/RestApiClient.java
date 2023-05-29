package com.epam.clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestApiClient {

    public Response getRequest(String baseUrl, String endPoint, String authToken) {
        return given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer ".concat(authToken))
                .when()
                .get(endPoint);
    }

    public Response postRequest(String baseUrl, String endPoint, String authToken, Object body) {
        return given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer ".concat(authToken))
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post(endPoint);
    }

    public Response putRequest(String baseUrl, String endPoint, String authToken, Object body) {
        return given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer ".concat(authToken))
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .put(endPoint);
    }

    public Response deleteRequest(String baseUrl, String endPoint, String authToken) {
        return given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer ".concat(authToken))
                .when()
                .delete(endPoint);
    }

    public void checkStatusCode(Response response, int expectedStatusCode){
        response.then().log().all().assertThat().statusCode(expectedStatusCode);
    }

}
