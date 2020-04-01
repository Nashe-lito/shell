package api.tests;

import api.helpers.XLUtils;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class DriverCreateTests {
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
  public void testSendPostWithActualDataReturn200(String firstName, String lastName, String middleName, String email, String phone, String carsNumber, String status, String note) {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    JSONObject phoneNumber = new JSONObject();
    phoneNumber.put("number", "+" + phone);
    JSONArray ja = new JSONArray();
    ja.add(phoneNumber);

    JSONObject carNumber = new JSONObject();
    carNumber.put("number", carsNumber);
    JSONArray carNumbers = new JSONArray();
    carNumbers.add(carNumber);

    JSONObject requestParam = new JSONObject();
    requestParam.put("firstName", firstName);
    requestParam.put("lastName", lastName);
    requestParam.put("middleName", middleName);
    requestParam.put("email", email);
    requestParam.put("phones", ja);
    requestParam.put("carsNumbers", carNumbers);
    requestParam.put("status", status);
    requestParam.put("note", note);

    EncoderConfig.encoderConfig().encodeContentTypeAs("charset=utf-8", ContentType.TEXT);
    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response response = httpRequest.request(Method.POST, "/drivers/create");

    String responseBody = response.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = response.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);

    // Удалить мусор после теста
    String id = response.jsonPath().get("id");
    Response responseForDelete = httpRequest.request(Method.POST, "/drivers/delete/" + id);
  }

  @DataProvider(name= "getPositiveData")
  Object[][] getPositiveData(){
    String driverdata[][] = {{"Fn", "Fn", "Fn", "test@example.com" + new Random()
            .nextInt(1000), "380987165319", "AA12345AA", "active", "test note"},
            {"QWERTYUIOP ASDFGHJKL ZXCVBNM q", "QWERTYUIOP ASDFGHJKL ZXCVBNM q", "QWERTYUIOP ASDFGHJKL ZXCVBNM q", "em2!#$%&'*+-/=?^_`{|}~ail@google.com" + new Random()
                    .nextInt(1000), "380987165319", "!@#$%^&*", "blocked", "Ea eos harum eripuit delenit, ex duis omnes eirmod vel. Sed an debitis pericula consulatu. Eu facilis mentitum gloriatur est, blandit accumsan definitiones est cu. Mel audiam nominati sapientem ut, iisque adolescens usu ex. Simul aliquando est eu, pr"},
            {"Пупкин\'`- Иван", "Пупкин\'`-Иван", "П\'`-и", "123456789@google.com" + new Random()
                    .nextInt(1000), "380987165319", "AA12345AA", "active", "qwertyuioasdfghjklzxcvbnm !#$%&'*+-/=?^_`{|}~@aurocraft.com QWERTYUIOP ASDFGHJKL ZXCVBNM. 1234567890 йцукенгшщзфывапролдячсмитьб, ЙЦУКЕНГШЩЗ ФЫВАПРОЛД ЯЧСМИТЬБЮ ЇЄ. Lorem ipsum dolor sit amet, duo at odio nonumes consectetuer, paulo accusam temporibu"},
            {"Пупкин Иван", "Пупкин-Иван", "Пи", "emailemailemailemailemailemailemailemailemailemali@aurocraft.co" + new Random().nextInt(9), "380987165319", "AA12345AA", "active", ""}
    };
    return (driverdata);
  }


  //  "middleName": "J" принимает. завести баг. после фикса отредактировать файл
  @Test(dataProvider = "getNegativeData")
  public void testSendPostWithActualDataReturn400(String firstName, String lastName, String middleName, String email, String phone, String carsNumber, String status, String note) {
      RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

      RequestSpecification httpRequest = RestAssured.given().log().all()
              .auth().oauth2(token);

      JSONObject phoneNumber = new JSONObject();
      phoneNumber.put("number", "+" + phone);
      JSONArray ja = new JSONArray();
      ja.add(phoneNumber);

      JSONObject carNumber = new JSONObject();
      carNumber.put("number", carsNumber);
      JSONArray carNumbers = new JSONArray();
      carNumbers.add(carNumber);

      JSONObject requestParam = new JSONObject();
      requestParam.put("firstName", firstName);
      requestParam.put("lastName", lastName);
      requestParam.put("middleName", middleName);
      requestParam.put("email", email);
      requestParam.put("phones", ja);
      requestParam.put("carsNumbers", carNumbers);
      requestParam.put("status", status);
      requestParam.put("note", note);

    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response response = httpRequest.request(Method.POST, "/drivers/create");

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

    String path = System.getProperty("user.dir") + "/src/test/resources/NegativeDataForDriverFormFields.xlsx";
    int rownum = XLUtils.getRowCount(path, "Page1");
    int colcount = XLUtils.getCellCount(path, "Page1", 1);
    String driverdata[][] = new String[rownum][colcount];
    for (int i = 1; i <= rownum; i++){
      for (int j = 0; j < colcount; j++){
        driverdata[i - 1][j] = XLUtils.getCellData(path, "Page1", i, j);
      }
    }
    return (driverdata);
  }
}
