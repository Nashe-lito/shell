package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class ClientAuthTests {

  @Test
  public void testSendPostWithActualDataReturn200(){
    RestAssured.given()
            .baseUri("https://shell-b2b.test.aurocraft.com/api")
            .basePath("/v1/oauth")
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "  \"username\": \"johndou\",\n" +
                    "  \"password\": \"111\"\n" +
                    "}")
            .when().post()
            .then()
            .statusCode(200);
  }

  @Test
  public void testAuthorizationAsARegisteredUserWithOutPasswordReturn400(){
    RestAssured.given()
            .baseUri("https://shell-b2b.test.aurocraft.com/api")
            .basePath("/v1/oauth")
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "  \"username\": \"johndou\",\n" +
                    "  \"password\": \"\"\n" +
                    "}")
            .when().post()
            .then()
            .statusCode(400);
  }

@Test
  public void testAuthorizationAsARegisteredUserWithNullInPassworddReturn400(){
  RestAssured.given()
          .baseUri("https://shell-b2b.test.aurocraft.com/api")
          .basePath("/v1/oauth")
          .contentType(ContentType.JSON)
          .body("{\n" +
                  "  \"username\": \"johndou\",\n" +
                  "  \"password\": \"null\"\n" +
                  "}")
          .when().post()
          .then()
          .statusCode(400);
}

@Test
  public void testSendGetReturn405(){
  RestAssured.given()
          .baseUri("https://shell-b2b.test.aurocraft.com/api")
          .basePath("/v1/oauth")
          .contentType(ContentType.JSON)
          .body("{\n" +
                  "  \"username\": \"johndou\",\n" +
                  "  \"password\": \"111\"\n" +
                  "}")
          .when().get()
          .then()
          .statusCode(405);
}
  @Test
  public void testSendPutReturn405(){
    RestAssured.given()
            .baseUri("https://shell-b2b.test.aurocraft.com/api")
            .basePath("/v1/oauth")
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "  \"username\": \"johndou\",\n" +
                    "  \"password\": \"111\"\n" +
                    "}")
            .when().put()
            .then()
            .statusCode(405);
  }
  @Test
  public void testSendDeleteReturn405(){
    RestAssured.given()
            .baseUri("https://shell-b2b.test.aurocraft.com/api")
            .basePath("/v1/oauth")
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "  \"username\": \"johndou\",\n" +
                    "  \"password\": \"111\"\n" +
                    "}")
            .when().delete()
            .then()
            .statusCode(405);
  }

  @Test
  public void testAuthorizationWithIncorrectPasswordReturn400() {
    RestAssured.given()
            .baseUri("https://shell-b2b.test.aurocraft.com/api")
            .basePath("/v1/oauth")
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "  \"username\": \"johndou\",\n" +
                    "  \"password\": \"qwerty\"\n" +
                    "}")
            .when().post()
            .then()
            .statusCode(400);
  }

  @Test
  public void testAuthorizationWithIncorrectLoginReturn400(){
    RestAssured.given()
            .baseUri("https://shell-b2b.test.aurocraft.com/api")
            .basePath("/v1/oauth")
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "  \"username\": \"jоhndou\",\n" +
                    "  \"password\": \"111\"\n" +
                    "}")
            .when().post()
            .then()
            .statusCode(400);
  }

  @Test
  public void testAuthorizationWithSQLInjectionReturn400(){
    RestAssured.given()
            .baseUri("https://shell-b2b.test.aurocraft.com/api")
            .basePath("/v1/oauth")
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "  \"username\": \"jоhndou\",\n" +
                    "  \"password\": \"' OR 100=100 --\"\n" +
                    "}")
            .when().post()
            .then()
            .statusCode(400);
  }

  @Test
  public void testAuthorizationWithSQLInjectionDropTableReturn400(){
    RestAssured.given()
            .baseUri("https://shell-b2b.test.aurocraft.com/api")
            .basePath("/v1/oauth")
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "  \"username\": \"johndou\",\n" +
                    "  \"password\": \"'; DROP TABLE users --\"\n" +
                    "}")
            .when().post()
            .then()
            .statusCode(400);
  }
/*  public function testSendPostSpecialCharactersReturn200(ApiTester $I)
  {
    $data = [
    "email" => "em2!#$%&'*+-/=?^_`{|}~ail@google.com",
          "name" => "Li",
          "phone" => "+380989999999",
          "companyName" => "Co"
        ];
    $I->sendPOST('/api/v1/new-client/request', $data);

    $I->seeResponseCodeIs(200);
    $I->seeResponseContainsJson($data);

    public function testSendPost5SimbolsReturn400(ApiTester $I)
    {
        $data = [
            "email" => "e@g.c",
            "name" => "Li",
            "phone" => "+380989999999",
            "companyName" => "Co"
        ];
        $I->sendPOST('/api/v1/new-client/request', $data);

        $I->seeResponseCodeIs(400);
        $I->seeResponseEqualsJson([
            'errors' => [
                'email' => 'This value is not a valid email address.'
            ]
        ]);
    }


  }*/
}
