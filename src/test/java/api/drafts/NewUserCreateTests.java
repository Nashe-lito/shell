package api.drafts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class NewUserCreateTests {
  private static Response resp =  RestAssured.given()
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
  public void testSendPostWithActualDataReturn200() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";
/*    PreemptiveBasicAuthScheme authScheme= new PreemptiveBasicAuthScheme();
    authScheme.setUserName("johndou");
    authScheme.setPassword("111");
    RestAssured.authentication = authScheme;

    RequestSpecification httpRequest=RestAssured.given();
    Response resp = httpRequest.request(Method.POST, "/v1/oauth");

    httpRequest.header("Content-Type", "application/json");*/


    RequestSpecification httpRequest=RestAssured.given().log().all()
            .auth().oauth2(token);

    JSONObject requestParam = new JSONObject();
    requestParam.put("username", "John" + new Random().nextInt(1000));
    requestParam.put("email", "test@example.com" + new Random().nextInt(1000));
    requestParam.put("firstName", "Fn");
    requestParam.put("lastName", "Ln");
    requestParam.put("middleName", "Mn");
    requestParam.put("role", "admin");
    requestParam.put("password", "1qaz@WSX3edc");
    requestParam.put("phone", "+380987165319");

    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response response = httpRequest.request(Method.POST, "/company/employees/create");

    String responseBody = response.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = response.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);

    String status = response.jsonPath().getString("status");
    Assert.assertEquals(status, "active");

    String role = response.jsonPath().getString("role");
    Assert.assertEquals(role, "admin");
  }

  @Test
  public void testSendPostSpecialCharactersReturn200() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest=RestAssured.given().log().all()
            .auth().oauth2(token);

    JSONObject requestParam = new JSONObject();
    requestParam.put("username", "Testt" + new Random().nextInt(1000));
    requestParam.put("email",  new Random().nextInt(1000) + "em2!#$%&'*+-/=?^_`{|}~ail@google.com");
    requestParam.put("firstName", "Fn");
    requestParam.put("lastName", "Ln");
    requestParam.put("middleName", "Mn");
    requestParam.put("role", "manager");
    requestParam.put("password", "1qaz@WSX3edc");
    requestParam.put("phone", "+380987165319");

    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response response = httpRequest.request(Method.POST, "/company/employees/create");

    String responseBody = response.getBody().asString();
    System.out.println("Response Body is: " + responseBody);
    Assert.assertEquals(responseBody.contains("Testt"), true);
    Assert.assertEquals(responseBody.contains("Fn"), true);
    Assert.assertEquals(responseBody.contains("Ln"), true);
    Assert.assertEquals(responseBody.contains("Mn"), true);
    Assert.assertEquals(responseBody.contains("manager"), true);
    Assert.assertEquals(responseBody.contains("+380987165319"), true);

    int statusCode = response.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);

   /* String status = response.jsonPath().getString("status");
    Assert.assertEquals(status, "active");

    String role = response.jsonPath().getString("role");
    Assert.assertEquals(role, "manager");*/
  }

  @Test(dataProvider = "positivetest")
 void testsReturn200(String username, String email, String firstName, String lastName, String middleName, String role, String password, String phone) {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest=RestAssured.given().log().all()
            .auth().oauth2(token);

    JSONObject requestParam = new JSONObject();
    requestParam.put("username", username);
    requestParam.put("email",  email);
    requestParam.put("firstName", firstName);
    requestParam.put("lastName", lastName);
    requestParam.put("middleName", middleName);
    requestParam.put("role", role);
    requestParam.put("password", password);
    requestParam.put("phone", phone);

    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response response = httpRequest.request(Method.POST, "/company/employees/create");

    String responseBody = response.getBody().asString();
    System.out.println("Response Body is: " + responseBody);
    Assert.assertEquals(responseBody.contains(username), true);
    Assert.assertEquals(responseBody.contains(firstName), true);
    Assert.assertEquals(responseBody.contains(lastName), true);
    Assert.assertEquals(responseBody.contains(middleName), true);
    Assert.assertEquals(responseBody.contains(role), true);
    Assert.assertEquals(responseBody.contains(phone), true);

    int statusCode = response.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @DataProvider(name= "positivetest")
  Object[][] getData(){
    String userdata[][] = {{"Test" + new Random()
            .nextInt(1000),  "test@example.com" + new Random()
            .nextInt(1000), "Fn", "Fn", "Fn", "manager", "1qaz@WSX3edc", "+380987165319"},{"Test" + new Random()
            .nextInt(1000),  "test@example.com" + new Random()
            .nextInt(1000), "Fn", "Fn", "Fn", "manager", "1qaz@WSX3edc", "+380987165319"}};
    return (userdata);
  }

    @Test
  public void testSendPost5SimbolsReturn400(){
    Response resp1=  RestAssured.given()
            .log().all()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .when()
            .body("{\n" +
                    "\"username\": \"Li\",\n" +
                    "\"email\": \"e@g.c\",\n" +
                    "\"firstName\": \"Jo\",\n" +
                    "\"lastName\": \"Sm\",\n" +
                    "\"middleName\": \"Sm\",\n" +
                    "\"role\": \"admin\",\n" +
                    "\"password\": \"drowssaP123\",\n" +
                    "\"phone\": \"+380987165319\"\n" +
                    "}")
            .post("https://shell-b2b.test.aurocraft.com/api/v1/company/employees/create");

    String responseBody = resp1.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = resp1.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 400);

    String statusLine = resp1.getStatusLine();
    System.out.println(statusLine);
    Assert.assertEquals(statusLine, "HTTP/1.1 400 Bad Request");
  }

  @Test
  public void testSendPost65SimbolsReturnBadRequest400(){
    Response response =  RestAssured.given()
            .log().all()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .when()
            .body("{\n" +
                    "\"username\": \"User1\",\n" +
                    "\"email\": \"emailEmailEmailEmailEmailEmailEmailEmailEmailEmalil@aurocraft.com\",\n" +
                    "\"firstName\": \"J\",\n" +
                    "\"lastName\": \"S\",\n" +
                    "\"middleName\": \"S\",\n" +
                    "\"role\": \"admin\",\n" +
                    "\"password\": \"1234567\",\n" +
                    "\"phone\": \"+38098716531\"\n" +
                    "}")
            .post("https://shell-b2b.test.aurocraft.com/api/v1/company/employees/create");
         //   .then()
           // .log().all()
           // .statusCode(400)
           //  .body("name", equalTo("ТОВАРИСТВО З ОБМЕЖЕНОЮ ВІДПОВІДАЛЬНІСТЮ «НЕК-СУС ІНЖИНІРІНГ»"))
         //   .extract()
           // .response();
 //   resp1.getBody().print();
    String responseBody = response.getBody().asString();
    System.out.println("Response Body is: " + responseBody);
 // Assert.assertTrue(responseBody.contains("Значення занадто коротке."));
   /* Assert.assertEquals(responseBody,
            "{\"errors\":{\"firstName\":[\"\\u0417\\u043d\\u0430\\u0447\\u0435\\u043d\\u043d\\u044f \\u0437\\u0430\\u043d\\u0430\\u0434\\u0442\\u043e \\u043a\\u043e\\u0440\\u043e\\u0442\\u043a\\u0435. \\u041f\\u043e\\u0432\\u0438\\u043d\\u043d\\u043e \\u0431\\u0443\\u0442\\u0438 \\u0440\\u0456\\u0432\\u043d\\u0435 2 \\u0441\\u0438\\u043c\\u0432\\u043e\\u043b\\u0430\\u043c \\u0430\\u0431\\u043e \\u0431\\u0456\\u043b\\u044c\\u0448\\u0435.\"],\"middleName\":[\"\\u0417\\u043d\\u0430\\u0447\\u0435\\u043d\\u043d\\u044f \\u0437\\u0430\\u043d\\u0430\\u0434\\u0442\\u043e \\u043a\\u043e\\u0440\\u043e\\u0442\\u043a\\u0435. \\u041f\\u043e\\u0432\\u0438\\u043d\\u043d\\u043e \\u0431\\u0443\\u0442\\u0438 \\u0440\\u0456\\u0432\\u043d\\u0435 2 \\u0441\\u0438\\u043c\\u0432\\u043e\\u043b\\u0430\\u043c \\u0430\\u0431\\u043e \\u0431\\u0456\\u043b\\u044c\\u0448\\u0435.\"],\"lastName\":[\"\\u0417\\u043d\\u0430\\u0447\\u0435\\u043d\\u043d\\u044f \\u0437\\u0430\\u043d\\u0430\\u0434\\u0442\\u043e \\u043a\\u043e\\u0440\\u043e\\u0442\\u043a\\u0435. \\u041f\\u043e\\u0432\\u0438\\u043d\\u043d\\u043e \\u0431\\u0443\\u0442\\u0438 \\u0440\\u0456\\u0432\\u043d\\u0435 2 \\u0441\\u0438\\u043c\\u0432\\u043e\\u043b\\u0430\\u043c \\u0430\\u0431\\u043e \\u0431\\u0456\\u043b\\u044c\\u0448\\u0435.\"],\"email\":[\"\\u0417\\u043d\\u0430\\u0447\\u0435\\u043d\\u043d\\u044f \\u0437\\u0430\\u043d\\u0430\\u0434\\u0442\\u043e \\u0434\\u043e\\u0432\\u0433\\u0435. \\u041f\\u043e\\u0432\\u0438\\u043d\\u043d\\u043e \\u0431\\u0443\\u0442\\u0438 \\u0440\\u0456\\u0432\\u043d\\u0435 64 \\u0441\\u0438\\u043c\\u0432\\u043e\\u043b\\u0430\\u043c \\u0430\\u0431\\u043e \\u043c\\u0435\\u043d\\u0448\\u0435.\"],\"password\":[\"\\u0417\\u043d\\u0430\\u0447\\u0435\\u043d\\u043d\\u044f \\u0437\\u0430\\u043d\\u0430\\u0434\\u0442\\u043e \\u043a\\u043e\\u0440\\u043e\\u0442\\u043a\\u0435. \\u041f\\u043e\\u0432\\u0438\\u043d\\u043d\\u043e \\u0431\\u0443\\u0442\\u0438 \\u0440\\u0456\\u0432\\u043d\\u0435 6 \\u0441\\u0438\\u043c\\u0432\\u043e\\u043b\\u0430\\u043c \\u0430\\u0431\\u043e \\u0431\\u0456\\u043b\\u044c\\u0448\\u0435.\"],\"phone\":[\"\\u0417\\u043d\\u0430\\u0447\\u0435\\u043d\\u043d\\u044f \\u043f\\u043e\\u0432\\u0438\\u0438\\u043d\\u043e \\u0431\\u0443\\u0442\\u0438 \\u0440\\u0456\\u0432\\u043d\\u0438\\u043c 13 \\u0441\\u0438\\u043c\\u0432\\u043e\\u043b\\u0430\\u043c.\"]}}");
         */

    int statusCode = response.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 400);

    String statusLine = response.getStatusLine();
    System.out.println(statusLine);
    Assert.assertEquals(statusLine, "HTTP/1.1 400 Bad Request");

   /* String error = resp1.jsonPath().get("firstName");
    Assert.assertEquals(error, "Значення занадто коротке. Повинно бути рівне 2 символам або більше.");
  */
/*
 JsonPath jsonpath = response.jsonPath();
   System.out.println(java.util.Optional.ofNullable(jsonpath.get("firstName")));
    Assert.assertEquals(jsonpath.get("firstName"), "Значення занадто коротке. Повинно бути рівне 2 символам або більше.") ;
*/

  }

  @Test
  public void testSendPostWithNoDataReturnBadRequest400() {
    Response response = RestAssured.given()
            .log().all()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .when()
            .body("")
            .post("https://shell-b2b.test.aurocraft.com/api/v1/company/employees/create");

    String responseBody = response.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = response.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 400);

    String statusLine = response.getStatusLine();
    System.out.println(statusLine);
    Assert.assertEquals(statusLine, "HTTP/1.1 400 Bad Request");
  }

}
