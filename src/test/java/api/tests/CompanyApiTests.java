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

public class CompanyApiTests {
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
  public void testGetCompanyDashboard() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    Response responseDashboard = httpRequest.request(Method.GET, "/company/dashboard");

    String responseBody = responseDashboard.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = responseDashboard.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @Test
  public void testGetCompanyProfile() {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    Response responseProfile = httpRequest.request(Method.GET, "/company/profile");

    String responseBody = responseProfile.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = responseProfile.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @Test(dataProvider = "getPositiveData")
  public void testSendPostWithActualDataReturn200(String name, String email, String phone, String address) {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    JSONObject requestParam = new JSONObject();
    requestParam.put("name", name);
    requestParam.put("accountingEmail", email);
    requestParam.put("accountingPhone",  phone);
    requestParam.put("postalAddress", address);

    EncoderConfig.encoderConfig().encodeContentTypeAs("charset=utf-8", ContentType.TEXT);
    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response response = httpRequest.request(Method.POST, "/company/profile/update");

    String responseBody = response.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = response.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @DataProvider(name = "getPositiveData")
  Object[][] getPositiveData(){
    String CompanyProfileData[][]= {{"E", "test@example.com" + new Random()
            .nextInt(1000), "+380987165319", "T"},
            {"qwertyuioasdfghjklzxcvbnm !#$%&'*+-/=?^_`{|}~@aurocraft.com QWERTYUIOP ASDFGHJKL ZXCVBNM. 1234567890 йцукенгшщзфывапролдячсмитьб, ЙЦУКЕНГШЩЗ ФЫВАПРОЛД ЯЧСМИТЬБЮ ЇЄ. Lorem ipsum dolor sit amet, qwertyu", "em2!#$%&'*+-/=?^_`{|}~ail@google.com" + new Random()
                    .nextInt(1000), "", "qwertyuioasdfghjklzxcvbnm !#$%&'*+-/=?^_`{|}~@aurocraft.com QWERTYUIOP ASDFGHJKL ZXCVBNM. 1234567890 йцукенгшщзфывапролдячсмитьб, ЙЦУКЕНГШЩЗ ФЫВАПРОЛД ЯЧСМИТЬБЮ ЇЄ. Lorem ipsum dolor sit amet, qwertyuioasdfghjklzxcvbnm !#$%&'*+-/=?^_`{|}~@aurocrafddd"},
            {"E", "", "", ""},
    };
    return (CompanyProfileData);
  }

  @Test(dataProvider = "getNegativeData")
  public void testSendPostWithActualDataReturn400(String name, String email, String phone, String address) {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    JSONObject requestParam = new JSONObject();
    requestParam.put("name", name);
    requestParam.put("accountingEmail", email);
    requestParam.put("accountingPhone", "+" + phone);
    requestParam.put("postalAddress", address);


    EncoderConfig.encoderConfig().encodeContentTypeAs("charset=utf-8", ContentType.TEXT);
    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response responseFeedback = httpRequest.request(Method.POST, "/company/profile/update");
    String responseBody = responseFeedback.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = responseFeedback.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 400);
  }

  @DataProvider(name= "getNegativeData")
  Object[][] getNegativeData() throws IOException {
    String path = System.getProperty("user.dir") + "/src/test/resources/NegativeDataForCompanyFormFields.xlsx";
    int rownum = XLUtils.getRowCount(path, "Page1");
    int colcount = XLUtils.getCellCount(path, "Page1", 1);
    String copmanydata[][] = new String[rownum][colcount];
    for (int i = 1; i <= rownum; i++){
      for (int j = 0; j < colcount; j++){
        copmanydata[i - 1][j] = XLUtils.getCellData(path, "Page1", i, j);
      }
    }
    return (copmanydata);
  }
}
