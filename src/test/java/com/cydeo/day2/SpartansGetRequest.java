package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartansGetRequest {
   String url = "http://44.208.34.43:8000";


   @Test
   public void test1() {

//    Given Accept type application/json
//    When user send GET request to api/spartans end point
//    Then status code must 200
//    And response Content Type must be application/json
//    And response body should include spartan result

      Response response = RestAssured.given().accept(ContentType.JSON)
              .get(url + "/api/spartans");
      System.out.println(response.statusCode());

      System.out.println("response.contentType() = " + response.contentType());
      Assertions.assertEquals(200,response.statusCode());
      Assertions.assertEquals("application/json",response.contentType());
      response.prettyPrint();

   }

   @Test
   public void test2(){
       /*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

      Response response = RestAssured.given().accept(ContentType.JSON)
              .when().get(url + "/api/spartans/3");

      System.out.println("response.statusCode() = " + response.statusCode());
      System.out.println("response.contentType() = " + response.contentType());
      Assertions.assertEquals(200,response.statusCode());
      Assertions.assertEquals("application/json",response.contentType());
      Assertions.assertTrue(response.body().asString().contains("Fidole"));
   }

   /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */
   @Test
   public void test3(){
      Response response = RestAssured.when().get(url + "/api/hello");

      Assertions.assertEquals(200,response.statusCode());
      Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());
      Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());
      Assertions.assertTrue(response.headers().hasHeaderWithName("date"));
      Assertions.assertTrue(response.header("Content-Length").equals("17"));
      Assertions.assertTrue(response.body().asString().equals("Hello from Sparta"));





   }
}
