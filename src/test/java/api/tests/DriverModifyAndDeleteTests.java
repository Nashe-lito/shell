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

public class DriverModifyAndDeleteTests {
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
  public void testModifyDriver() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    JSONObject phoneNumber = new JSONObject();
    phoneNumber.put("number", "+380936393631");
    JSONArray ja = new JSONArray();
    ja.add(phoneNumber);

    JSONObject carNumber = new JSONObject();
    carNumber.put("number", "AA22GG");
    JSONArray carNumbers = new JSONArray();
    carNumbers.add(carNumber);

    String id = createDriverAndGetId(httpRequest);

    JSONObject requestEditParam = new JSONObject();
    requestEditParam.put("firstName", "firstNameUpd");
    requestEditParam.put("lastName", "lastNameUpd");
    requestEditParam.put("middleName", "middleNameUpd");
    requestEditParam.put("email", "upd@test.test" + new Random().nextInt(1000));
    requestEditParam.put("phones", ja);
    requestEditParam.put("carsNumbers", carNumbers);
    requestEditParam.put("status", "blocked");
    requestEditParam.put("note", "Test");

    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestEditParam.toJSONString());

    Response responseForEdit = httpRequest.request(Method.POST, "/drivers/update/" + id);
    String responseBody = responseForEdit.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    Assert.assertEquals(responseBody.contains("firstNameUpd"), true);
    Assert.assertEquals(responseBody.contains("lastNameUpd"), true);
    Assert.assertEquals(responseBody.contains("middleNameUpd"), true);
    Assert.assertEquals(responseBody.contains("upd@test.test"), true);
    Assert.assertEquals(responseBody.contains("Test"), true);

    int statusCode = responseForEdit.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);

    // Удалить мусор после теста
    Response responseForDelete = httpRequest.request(Method.POST, "/drivers/delete/" + id);
  }

  @Test
  public void testChangeStatusOfUser() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    String id = createDriverAndGetId(httpRequest);

    JSONObject requestEditParam = new JSONObject();
    requestEditParam.put("status", "blocked");

    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestEditParam.toJSONString());

    Response responseForChangeStatus = httpRequest.request(Method.POST, "/drivers/change-status/" + id);

    String responseBody = responseForChangeStatus.getBody().asString();
    Assert.assertEquals(responseBody, "{\"success\":true}");

    int statusCode = responseForChangeStatus.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);

    // Удалить мусор после теста
    Response responseForDelete = httpRequest.request(Method.POST, "/drivers/delete/" + id);
  }

  @Test
  public void testDeleteUser() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    String id = createDriverAndGetId(httpRequest);

    Response responseForDelete = httpRequest.request(Method.POST, "/drivers/delete/" + id);
    String responseBody = responseForDelete.getBody().asString();
    Assert.assertEquals(responseBody, "{\"success\":true}");

    int statusCode = responseForDelete.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @Test
  public void testGetList(){
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    Response responseList = httpRequest.request(Method.GET, "/drivers");

    String responseBody = responseList.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    Assert.assertEquals(responseBody.contains("meta"), true);

    int statusCode = responseList.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @Test
  public void testGetRead(){
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    String id = createDriverAndGetId(httpRequest);

    Response responseList = httpRequest.request(Method.GET, "/drivers/" + id);

    String responseBody = responseList.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    Assert.assertEquals(responseBody.contains("firstName"), true);

    int statusCode = responseList.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);

    // Удалить мусор после теста
    Response responseForDelete = httpRequest.request(Method.POST, "/drivers/delete/" + id);
  }

  @Test
  public void testGetSearch(){
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    Response responseList = httpRequest.request(Method.GET, "/drivers/search");

    String responseBody = responseList.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = responseList.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  private String createDriverAndGetId(RequestSpecification httpRequest) {
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
    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response responseForCreate = httpRequest.request(Method.POST, "/drivers/create");

    return responseForCreate.jsonPath().get("id");
  }
}
