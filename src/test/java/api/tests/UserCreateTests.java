package api.tests;

import api.helpers.XLUtils;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class UserCreateTests {
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

  @Test(dataProvider = "getPositiveData")
  public void testSendPostWithActualDataReturn200(String username, String email, String firstName, String lastName, String middleName, String role, String password, String phone) {
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
    requestParam.put("phone", "+" + phone);
    EncoderConfig.encoderConfig().encodeContentTypeAs("charset=utf-8", ContentType.TEXT);
    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response response = httpRequest.request(Method.POST, "/company/employees/create");

    String responseBody = response.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

/*    String contentType = response.header("Content-Type");
    System.out.println("Content Type is " + contentType);
    Assert.assertEquals(contentType, "application/json");

    String contentEncoding = response.header("Content-Encoding");
    System.out.println("Content Type is " + contentEncoding);
    Assert.assertEquals(contentEncoding, "gzip");*/

    Assert.assertEquals(responseBody.contains(username), true);
   /* Assert.assertEquals(responseBody.contains(firstName), true);
    Assert.assertEquals(responseBody.contains(lastName), true);
   Assert.assertEquals(responseBody.contains(middleName), true);*/
    Assert.assertEquals(responseBody.contains(role), true);
    Assert.assertEquals(responseBody.contains(phone), true);

    int statusCode = response.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);

    // Удалить мусор после теста
    String id = response.jsonPath().get("id");
    Response responseForDelete  = httpRequest.request(Method.POST, "/company/employees/delete/" + id);
  }


  @DataProvider(name= "getPositiveData")
  Object[][] getPositiveData(){
    String userdata[][] = {{"Test" + new Random()
            .nextInt(1000),  "test@example.com" + new Random()
            .nextInt(1000), "Fn", "Fn", "Fn", "admin", "1qaz@WSX3edc", "380987165319"},
            {"Test" + new Random()
            .nextInt(1000),  "em2!#$%&'*+-/=?^_`{|}~ail@google.com" + new Random()
            .nextInt(1000), "QWERTYUIOP ASDFGHJKL ZXCVBNM q", "QWERTYUIOP ASDFGHJKL ZXCVBNM q", "QWERTYUIOP ASDFGHJKL ZXCVBNM q", "manager", "1qaz@WSX3edc", "380987165319"},
            {"Test" + new Random()
            .nextInt(1000),  "123456789@google.com" + new Random()
            .nextInt(1000), "Пупкин Иван", "Пупкин-Иван", "Пи", "accountant", "1qaz@WSX3edc", "380987165319"},
            {"Test" + new Random()
                    .nextInt(1000),  "emailemailemailemailemailemailemailemailemailemali@aurocraft.co" + new Random()
                    .nextInt(9), "Пупкин Иван", "Пупкин-Иван", "Пи", "accountant", "1qaz@WSX3edc", "380987165319"}};
    return (userdata);
  }

  @Test(dataProvider = "getNegativeData")
  public void testSendPostWithActualDataReturn400(String username, String email, String firstName, String lastName, String middleName, String role, String password, String phone) {
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
    requestParam.put("phone", "+" + phone);

    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response response = httpRequest.request(Method.POST, "/company/employees/create");

    String responseBody = response.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = response.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 400);

    String statusLine = response.getStatusLine();
    System.out.println(statusLine);
    Assert.assertEquals(statusLine, "HTTP/1.1 400 Bad Request");
  }

  @DataProvider(name= "getNegativeData")
  Object[][] getNegativeData() throws IOException {

    String path = System.getProperty("user.dir") + "/src/test/resources/NegativeDataForUserFormFields.xlsx";
    int rownum = XLUtils.getRowCount(path, "Page1");
    int colcount = XLUtils.getCellCount(path, "Page1", 1);
    String userdata[][] = new String[rownum][colcount];
    for (int i = 1; i <= rownum; i++){
      for (int j = 0; j < colcount; j++){
        userdata[i - 1][j] = XLUtils.getCellData(path, "Page1", i, j);
      }
    }
    return (userdata);
  }
}
