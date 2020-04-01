package api.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class UserModifyAndDeleteTests {
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
  public void testModifyUser() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";
    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    String id = createUserAndGetId(httpRequest);

    JSONObject requestEditParam = new JSONObject();
    requestEditParam.put("username", "usernameUpd" + new Random().nextInt(1000));
    requestEditParam.put("email", "testUpd@test.test" + new Random().nextInt(1000));
    requestEditParam.put("firstName", "FirstNameUpd");
    requestEditParam.put("lastName", "LastNameUpd");
    requestEditParam.put("role", "accountant");
    requestEditParam.put("password", "12345678");

    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestEditParam.toJSONString());

    Response response2 = httpRequest.request(Method.POST, "/company/employees/update/" + id);
    String responseBody = response2.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    Assert.assertEquals(responseBody.contains("usernameUpd"), true);
    Assert.assertEquals(responseBody.contains("testUpd@test.test"), true);
    Assert.assertEquals(responseBody.contains("FirstNameUpd"), true);
    Assert.assertEquals(responseBody.contains("LastNameUpd"), true);
    Assert.assertEquals(responseBody.contains("accountant"), true);

    int statusCode = response2.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);

    // Удалить мусор после теста
    Response responseForDelete = httpRequest.request(Method.POST, "/company/employees/delete/" + id);
  }


  @Test
  public void testChangeStatusOfUser() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";
    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    String id = createUserAndGetId(httpRequest);

    JSONObject requestEditParam = new JSONObject();
    requestEditParam.put("status", "blocked");

    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestEditParam.toJSONString());

    Response response2 = httpRequest.request(Method.POST, "/company/employees/change-status/" + id);

    String responseBody = response2.getBody().asString();
    Assert.assertEquals(responseBody, "{\"success\":true}");

    int statusCode = response2.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);

    // Удалить мусор после теста
    Response responseForDelete = httpRequest.request(Method.POST, "/company/employees/delete/" + id);
  }

  @Test
  public void testDeleteUser() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";
    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    String id = createUserAndGetId(httpRequest);

    Response response2 = httpRequest.request(Method.POST, "/company/employees/delete/" + id);
    String responseBody = response2.getBody().asString();
    Assert.assertEquals(responseBody, "{\"success\":true}");

    int statusCode = response2.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @Test
  public void testGetList(){
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";
    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    Response responseList = httpRequest.request(Method.GET, "/company/employees");

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

    String id = createUserAndGetId(httpRequest);

    Response responseList = httpRequest.request(Method.GET, "/company/employees/" + id);

    String responseBody = responseList.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    Assert.assertEquals(responseBody.contains("firstName"), true);

    int statusCode = responseList.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  private String createUserAndGetId(RequestSpecification httpRequest) {
    JSONObject requestParam = new JSONObject();
    requestParam.put("username", "username" + new Random().nextInt(1000));
    requestParam.put("email", "test@test.test" + new Random().nextInt(1000));
    requestParam.put("firstName", "FirstName");
    requestParam.put("lastName", "LastName");
    requestParam.put("role", "admin");
    requestParam.put("password", "1qaz@WSX3edc");

    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response response1 = httpRequest.request(Method.POST, "/company/employees/create");

    return response1.jsonPath().get("id");
  }
}
