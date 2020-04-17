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

public class InvoiceTests {
  private static Response resp = RestAssured.given()
          .baseUri("https://shell-b2b.test.aurocraft.com/api")
          .basePath("/v1/oauth")
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
  String token = resp.jsonPath().get("token");

  @Test
  public void testCreditDebt() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    Response responseCreditDebt = httpRequest.request(Method.GET, "/invoice/credit-debt");

    String responseBody = responseCreditDebt.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = responseCreditDebt.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @Test
  public void testListOfSupplies() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    Response responseListOfSupplies = httpRequest.request(Method.GET, "/supplies");

    String responseBody = responseListOfSupplies.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = responseListOfSupplies.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @Test
  public void testAmountSendPostWithActualDataReturn200() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);
    JSONObject requestParam = new JSONObject();
    requestParam.put("amount", 5000);

    EncoderConfig.encoderConfig().encodeContentTypeAs("charset=utf-8", ContentType.TEXT);
    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response responseCreateFormAmount = httpRequest.request(Method.POST, "/invoice/amount");

    String responseBody = responseCreateFormAmount.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = responseCreateFormAmount.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @Test
  public void testAmountSendPostWithActualDataReturn400() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);
    JSONObject requestParam = new JSONObject();
    requestParam.put("amount", "qwert!@#$%^&*");

    EncoderConfig.encoderConfig().encodeContentTypeAs("charset=utf-8", ContentType.TEXT);
    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response responseCreateFormAmount = httpRequest.request(Method.POST, "/invoice/amount");

    String responseBody = responseCreateFormAmount.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = responseCreateFormAmount.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 400);
  }


  @Test
  public void testSuppliesSendPostWithActualDataReturn200() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);
    String id = "38b6cc1c-e323-4f53-b052-fb5b5e731ae7";
    String volume = "300";

    JSONObject itemsBody = new JSONObject();
    itemsBody.put("id", id);
    itemsBody.put("volume", volume);

    JSONArray items = new JSONArray();
    items.add(itemsBody);

    JSONObject requestParam = new JSONObject();
    requestParam.put("items", items);

    EncoderConfig.encoderConfig().encodeContentTypeAs("charset=utf-8", ContentType.TEXT);
    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response responseCreateFormAmount = httpRequest.request(Method.POST, "/invoice/supplies");

    String responseBody = responseCreateFormAmount.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = responseCreateFormAmount.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @Test
  public void testSuppliesSendPostWithActualDataReturn400() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);
    JSONObject requestParam = new JSONObject();
    requestParam.put("amount", "qwert!@#$%^&*");

    EncoderConfig.encoderConfig().encodeContentTypeAs("charset=utf-8", ContentType.TEXT);
    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response responseCreateFormAmount = httpRequest.request(Method.POST, "/invoice/supplies");

    String responseBody = responseCreateFormAmount.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = responseCreateFormAmount.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 400);
  }


}
