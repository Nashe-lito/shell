package api.tests;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class TieDriverToCardTests {
  private static Response tokenResponse = RestAssured.given()
          .baseUri("https://shell-b2b.test.aurocraft.com/api/v1")
          .basePath("/oauth")
          .contentType(ContentType.JSON)
          .body("{\n" +
                  "  \"username\": \"johndou\",\n" +
                  "  \"password\": \"111\"\n" +
                  "}")
          .when().post()
          .then()
          .statusCode(200)
          .extract()
          .response();
  String token = tokenResponse.jsonPath().get("token");

  @Test
  public void testTieDriverToCard() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpsRequest = RestAssured.given().auth().oauth2(token);

    String id = createDriverAndGetId(httpsRequest);

    JSONObject requestTieDriverParam = new JSONObject();
    requestTieDriverParam.put("driverId", id);

    httpsRequest.header("Content-Type", "application/json");
    httpsRequest.body(requestTieDriverParam.toJSONString());

    Response responseForTieDriverToCard = httpsRequest.request(Method.POST, "/fuel-cards/change-driver/e08f76e3-85fd-439e-a9e9-a4199a0969df");

    String responseBody = responseForTieDriverToCard.getBody().asString();
    System.out.println("Response Body is: " + responseBody);
    Assert.assertTrue(responseBody.contains(id));

    int statusCode = responseForTieDriverToCard.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);

    // Удалить мусор после теста
    Response responseForDeleteDriver = httpsRequest.request(Method.POST, "/drivers/delete/" + id);
  }

  @Test
  public void testUntieDriverToCard() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";
    RequestSpecification httpsRequest = RestAssured.given().auth().oauth2(token);

    String id = createDriverAndGetId(httpsRequest);
    JSONObject requestTieDriverParam = new JSONObject();
    requestTieDriverParam.put("driverId", id);
    httpsRequest.header("Content-Type", "application/json");
    httpsRequest.body(requestTieDriverParam.toJSONString());
    Response responseForTieDriverToCard = httpsRequest.request(Method.POST, "/fuel-cards/change-driver/e08f76e3-85fd-439e-a9e9-a4199a0969df");

    Response requestUntieDriver = httpsRequest.request(Method.POST, "/fuel-cards/delete-driver/e08f76e3-85fd-439e-a9e9-a4199a0969df");
    String responseBody = requestUntieDriver.getBody().asString();
    Assert.assertEquals(responseBody, "{\"success\":true}");

    int statusCode = requestUntieDriver.getStatusCode();
    Assert.assertEquals(statusCode, 200);

    // Удалить мусор после теста
    Response responseForDeleteDriver = httpsRequest.request(Method.POST, "/drivers/delete/" + id);
  }

  private String createDriverAndGetId(RequestSpecification httpsRequest) {
    JSONObject phoneNumber = new JSONObject();
    phoneNumber.put("number", "+380936393631");
    JSONArray ja = new JSONArray();
    ja.add(phoneNumber);

    JSONObject carNumber = new JSONObject();
    carNumber.put("number", "AA22GG");
    JSONArray carNumbers = new JSONArray();
    carNumbers.add(carNumber);

    JSONObject requestParam = new JSONObject();
    requestParam.put("firstName", "firstName");
    requestParam.put("lastName", "lastName");
    requestParam.put("middleName", "middleName");
    requestParam.put("email", "test@test.test" + new Random().nextInt(1000));
    requestParam.put("phones", ja);
    requestParam.put("carsNumbers", carNumbers);
    requestParam.put("status", "active");
    requestParam.put("note", "");

    EncoderConfig.encoderConfig().encodeContentTypeAs("charset=utf-8", ContentType.TEXT);
    httpsRequest.header("Content-Type", "application/json");
    httpsRequest.body(requestParam.toJSONString());

    Response responseForCreate = httpsRequest.request(Method.POST, "/drivers/create");

    return responseForCreate.jsonPath().get("id");
  }
}
