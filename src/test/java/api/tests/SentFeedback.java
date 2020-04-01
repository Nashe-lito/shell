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

public class SentFeedback {
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

  @Test(dataProvider = "getPositiveData")
  public void testSendPostWithActualDataReturn200(String email, String name, String category, String comment) {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    JSONObject requestParam = new JSONObject();
    requestParam.put("email", email);
    requestParam.put("name", name);
    requestParam.put("category", category);
    requestParam.put("comment", comment);

    EncoderConfig.encoderConfig().encodeContentTypeAs("charset=utf-8", ContentType.TEXT);
    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response responseFeedback = httpRequest.request(Method.POST, "/feedback");
    String responseBody = responseFeedback.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = responseFeedback.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @DataProvider(name = "getPositiveData")
  Object[][] getPositiveData() {
    String feedbackdata[][] = {{"test@example.com" + new Random().nextInt(1000), "Li", "general-question", "test note"},
            {"em2!#$%&'*+-/=?^_`{|}~ail@google.com" + new Random()
                    .nextInt(1000), "QWERTYUIOP ASDFGHJKL ZXCVBNM qQWERTYUIOP ASDFGHJKL", "financial-issue", "Ea eos harum eripuit delenit, ex duis omnes eirmod vel. Sed an debitis pericula consulatu. Eu facilis mentitum gloriatur est, blandit accumsan definitiones est cu. Mel audiam nominati sapientem ut, iisque adolescens usu ex. Simul aliquando est eu, pr Ea eos harum eripuit delenit, ex duis omnes eirmod vel. Sed an debitis pericula consulatu. Eu facilis mentitum gloriatur est, blandit accumsan definitiones est cu. Mel audiam nominati sapientem ut, iisque adolescens usu ex. Simul aliquando est eu, p"},
            {"123456789@google.com" + new Random().nextInt(1000), "Пупкин\'`- Иван", "new-card-order", "qwertyuioasdfghjklzxcvbnm !#$%&'*+-/=?^_`{|}~@aurocraft.com QWERTYUIOP ASDFGHJKL ZXCVBNM. 1234567890 йцукенгшщзфывапролдячсмитьб, ЙЦУКЕНГШЩЗ ФЫВАПРОЛД ЯЧСМИТЬБЮ ЇЄ. Lorem ipsum dolor sit amet, duo at odio nonumes consectetuer, paulo accusam temporibu"},
            {"emailemailemailemailemailemailemailemailemailemali@aurocraft.co" + new Random().nextInt(9), "Пупкин Иван-Иван", "complaints", "Td"}
    };
    return (feedbackdata);
  }

  @Test(dataProvider = "getNegativeData")
  public void testSendPostWithActualDataReturn400(String email, String name, String category, String comment) {
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given().log().all()
            .auth().oauth2(token);

    JSONObject requestParam = new JSONObject();
    requestParam.put("email", email);
    requestParam.put("name", name);
    requestParam.put("category", category);
    requestParam.put("comment", comment);

    EncoderConfig.encoderConfig().encodeContentTypeAs("charset=utf-8", ContentType.TEXT);
    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParam.toJSONString());

    Response responseFeedback = httpRequest.request(Method.POST, "/feedback");
    String responseBody = responseFeedback.getBody().asString();
    System.out.println("Response Body is: " + responseBody);

    int statusCode = responseFeedback.getStatusCode();
    System.out.println("Status Code is: " + statusCode);
    Assert.assertEquals(statusCode, 400);
  }

  @DataProvider(name = "getNegativeData")
  Object[][] getNegativeData() throws IOException {

    String path = System.getProperty("user.dir") + "/src/test/resources/NegativeDataForFeedbackFormFields.xlsx";
    int rownum = XLUtils.getRowCount(path, "Page1");
    int colcount = XLUtils.getCellCount(path, "Page1", 1);
    String feedbackdata[][] = new String[rownum][colcount];
    for (int i = 1; i <= rownum; i++) {
      for (int j = 0; j < colcount; j++) {
        feedbackdata[i - 1][j] = XLUtils.getCellData(path, "Page1", i, j);
      }
    }
    return (feedbackdata);
  }
}
