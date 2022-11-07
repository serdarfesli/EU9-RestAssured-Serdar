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
}
