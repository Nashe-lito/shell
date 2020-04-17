package api.drafts;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class CreateNewUserOfCompanyTests {

//String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpYXQiOjE1ODIxMjEwNzQsImV4cCI6MTU4MjEyODI3NCwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJ1c2VybmFtZSI6ImpvaG5kb3UifQ.pkFmY5xZ628G_78uTMclvG2QgU2KjYFI8hE5DJRZI2i81w1PfgE-HTbaQCGwTES6sYQnR3CjyjGs95hQ33fd6bYyj6u0g18XVwcChxfTe_CvCPB5s7rBhMUTnp8CWa4jx0WssDKSXfqb64huNDEAu0m8jut4qhekY5gCZJRuNBwGLOX9mX7MkcqtlAnXuuwsp5qu1dWAWefDkRWxCSsYyGSbed6MA4xlw7WKeOphEZtGInMKBPu4-FoTBaAMd5Bz6QHTCBYW2jBHUHF5zrUzKR4o2jU4A2yQYOmEwjjXbAWeGapFAkUe6DCsnw_k6kaIHuW6RhqcV-4amejY59NjeIGECAgjGSOQiSI9bqsjlcdR0L_i3tRhYb9AUr2oGfn4BLQZiFJlwrRleFCKbarz0O_IqlzaLWB9qAvTuZcOj78fih-7aGzr8SIPWBT7zVpoxyLvzuR8jhd1_U_Ztllj9Z-zdlcdWcXEUJJTmNzyi3BHcAjiHhkEhM9RRJ0KSvAhFxMnIC1eb6kNcXgVL9rypmMmM0Z5NkNpS760uRRHmwCgVC_QZFIWNzD5LHNgr13RsuFEfsjnTxZeE6qmbFw0-BqdDbTGoEPgpUtGSTLWe44p_75yRVLahj9yDfiV-9BeXgWXs0aaUUguvYTec18gO_w8h7xxDUpEnbSexw_t1Qg";


  @BeforeMethod (enabled = false)
public void testSendPostWithActualDataReturn200(){
    Response tok = RestAssured.given()
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
tok.getBody().print();
}


  @Test (enabled = false)
 public void testSendPostWithActualDataReturn2001(){
 /*    Map<String, String> data = new HashMap<>();
    data.put("{\n" +
            "  \"username\": \"johndou\",\n" +
            "  \"password\": \"111\"\n" +
            "}");*/
//А далее
    Response resp=  RestAssured.given()
           /*.log().all()
            .auth().oauth2(tok)*/
            .when()
            .body("{\n" +
                    "\"name\": \"ТОВАРИСТВО З ОБМЕЖЕНОЮ ВІДПОВІДАЛЬНІСТЮ «НЕК-СУС ІНЖИНІРІНГ»\",\n" +
                    "\"accountingEmail\": \"test@test.test\",\n" +
                    "\"accountingPhone\": \"+380963332121\",\n" +
                    " \"directorEmail\": \"valer123ij72@mail.ru\",\n" +
                    "\"postalAddress\": \"test\"\n" +
                    "}")
    .post("https://shell-b2b.test.aurocraft.com/api/v1/company/profile/update")
            .then()
            .log().all()
            .statusCode(200)
.extract()
            .response();
    resp.getBody().print();
  }


  @Test(enabled = false)
  public void testGet()
  {
    Response resp=RestAssured
            .given()
            .log().all()
           // .auth().oauth2(token)
            .when()
            .get("https://shell-b2b.test.aurocraft.com/api/v1/company/employees?status=active")
            .then()
            .log().all()
            .statusCode(200)
            //.body( "totalCount",equalTo("1"))
            .extract()
            .response();
    resp.getBody().print();
  }


  @Test(enabled = false)
  public void testPOST() {
    long threadId = Thread.currentThread().getId();
    System.out.println("Thread Post:" + threadId);
    Map<String, String> data = new HashMap<>();
    data.put("orderId", "2");
    given()
            .contentType("application/json")
            .body(data)
            .when().post("http://httpbin.org/post").then()
            .statusCode(200)
            .body("json.orderId", equalTo("2"));
  }

  @Test(enabled = false)
 public void test1SendPostWithActualDataReturn200() throws Exception {
RestAssured.given()
           .baseUri("https://shell-b2b.test.aurocraft.com/api/v1/oauth")
           .contentType(ContentType.JSON)
           .formParam("username", "johndou")
           .formParam("password", "111")
          .body("{\n" +
                   "  \"username\": \"johndou\",\n" +
                   "  \"password\": \"111\"\n" +
                   "}")
           .when().post()
           .then()
           .statusCode(200);
  RestAssured.given().auth().basic("johndou", "111").expect().statusCode(200).when().get("https://shell-b2b.test.aurocraft.com/api/v1/company/profile");

 }

  @Test (enabled = false)
  public void
  oauth2_works_with_preemptive_header_signing() {
    final String accessToken = "accessToken";
    Response resp =  RestAssured
            .given()
            .auth().preemptive().oauth2(accessToken)
            .filter(new Filter() {
              public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
                assertThat(requestSpec.getHeaders().getValue("Authorization"), equalTo("Bearer "+accessToken));
                return new ResponseBuilder().setBody("ok").setStatusCode(200).build();
              }
            })
            .when()
            .body("{\n" +
                    "\"name\": \"ТОВАРИСТВО З ОБМЕЖЕНОЮ ВІДПОВІДАЛЬНІСТЮ «НЕК-СУС ІНЖИНІРІНГ»\",\n" +
                    "\"accountingEmail\": \"test@test.test\",\n" +
                    "\"accountingPhone\": \"+380963332121\",\n" +
                    " \"directorEmail\": \"valer123ij72@mail.ru\",\n" +
                    "\"postalAddress\": \"test\"\n" +
                    "}")
            .post("https://shell-b2b.test.aurocraft.com/api/v1/company/profile/update")
            .then()
            .log().all()
            .statusCode(200)
            //.body("name", equalTo("ТОВАРИСТВО З ОБМЕЖЕНОЮ ВІДПОВІДАЛЬНІСТЮ «НЕК-СУС ІНЖИНІРІНГ»"))
            .extract()
            .response();
    resp.getBody().print();
    String token = resp.jsonPath().get("access_token");
  }

  @Test//(enabled = false)
  public void test2SendPostWithActualDataReturn200() throws Exception {
/*
    Response resp = RestAssured.given()
           // .log().all()
            //.contentType("application/json")
         .contentType(ContentType.JSON)
            .formParam("username", "johndou")
            .formParam("password", "111")
            .post("https://shell-b2b.test.aurocraft.com/api/v1/oauth");*/

    Response resp =  RestAssured.given()
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
    resp.getBody().print();

    String token = resp.jsonPath().get("token");
    System.out.println(resp.getBody().toString());
   Assert.assertEquals(resp.getStatusCode(), 200);

  Response resp1=RestAssured
            .given()
            .log().all()
             .auth().oauth2(token)
          //  .when()
            .get("https://shell-b2b.test.aurocraft.com/api/v1/company/employees?status=active")
           .then()
            .log().all()
            .statusCode(200)
            //.body( "totalCount",equalTo("1"))
            .extract()
            .response();
    resp.getBody().print();
    System.out.println("Code is " + resp1.getStatusCode());
    System.out.println(resp.getBody().asString());
    Assert.assertEquals(resp1.getStatusCode(), 200);
  }

  private static String payload = "{\n" +
          " \"name\": \"TestApiTest\",\n" +
          " \"accountingEmail\": \"test@test.test\",\n" +
          " \"accountingPhone\": \"+380963332121\",\n" +
          " \"directorEmail\": \"valer123ij72@mail.ru\",\n" +
          " \"postalAddress\": \"test\"\n" +
          "}";

  @Test //(enabled = false)
  public void testSendPostWithActualDataReturn200Ok(){

    Response resp =  RestAssured.given()
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

    Response resp1=  RestAssured.given()
            .log().all()
             .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .when()
            .body(payload)
            .post("https://shell-b2b.test.aurocraft.com/api/v1/company/profile/update")
            .then()
            .log().all()
            .statusCode(200)
          //  .body("name", equalTo("ТОВАРИСТВО З ОБМЕЖЕНОЮ ВІДПОВІДАЛЬНІСТЮ «НЕК-СУС ІНЖИНІРІНГ»"))
            .extract()
            .response();
    resp1.getBody().print();
  }

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

  @Test// (enabled = false)
  public void testSendPostWithActualDataReturn200CreateUser(){
    Response resp1=  RestAssured.given()
            .log().all()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .when()
            .body("{\n" +
                    "\"username\": \"John78111111\",\n" +
                    "\"email\": \"john.smith@example.com1111\",\n" +
                    "\"firstName\": \"John\",\n" +
                    "\"lastName\": \"Smith\",\n" +
                    "\"middleName\": \"Smith\",\n" +
                    "\"role\": \"admin\",\n" +
                    "\"password\": \"drowssaP123\",\n" +
                    "\"phone\": \"+380987165319\"\n" +
                    "}")
            .post("https://shell-b2b.test.aurocraft.com/api/v1/company/employees/create")
            .then()
            .log().all()
            .statusCode(200)
            //  .body("name", equalTo("ТОВАРИСТВО З ОБМЕЖЕНОЮ ВІДПОВІДАЛЬНІСТЮ «НЕК-СУС ІНЖИНІРІНГ»"))
            .extract()
            .response();
    resp1.getBody().print();

  }
}
