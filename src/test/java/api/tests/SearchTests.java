package api.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTests {
  private static Response aythResponse = RestAssured.given()
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
  String token = aythResponse.jsonPath().get("token");

  @Test
  public void testGetFuelSearch(){
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";
    RequestSpecification httpRequest = RestAssured.given().log().all().auth().oauth2(token);

       Response responseFuel = httpRequest.request(Method.GET, "/fuel/search?q=Газ");

       String responseBody = responseFuel.getBody().asString();
    System.out.println("Response body is: " + responseBody);

    int statusCode = responseFuel.getStatusCode();
    System.out.println("Status code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @Test
  public void testGetGoodsSearch(){
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";
    RequestSpecification httpRequest = RestAssured.given().log().all().auth().oauth2(token);

    Response responseGoods = httpRequest.request(Method.GET, "/goods/search?q=Омивач");

    String responseBody = responseGoods.getBody().asString();
    System.out.println("Response body is: " + responseBody);

    int statusCode = responseGoods.getStatusCode();
    System.out.println("Status code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

  @Test
  public void testGetServicesSearch(){
    RestAssured.baseURI = "https://shell-b2b.test.aurocraft.com/api/v1";
    RequestSpecification httpRequest = RestAssured.given().log().all().auth().oauth2(token);

    Response responseServices = httpRequest.request(Method.GET, "/services/search?q=Автомийка Бізнес-A");

    String responseBody = responseServices.getBody().asString();
    System.out.println("Response body is: " + responseBody);

    int statusCode = responseServices.getStatusCode();
    System.out.println("Status code is: " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }

}
