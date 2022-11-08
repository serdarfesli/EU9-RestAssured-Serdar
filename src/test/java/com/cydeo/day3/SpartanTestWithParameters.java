package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartanTestWithParameters {
   @BeforeAll
   public static void init() {
      baseURI = "http://44.208.34.43:8000";
   }
   /*   Given accept type is Json
             And Id parameter value is 5
             When user sends GET request to /api/spartans/{id}
             Then response status code should be 200
             And response content-type: application/json
             And "Blythe" should be in response payload
          */
   @Test
   public void test1(){
      Response response = given().pathParam("id", 5)
              .when().get("/api/spartans/{id}");

      assertEquals(200,response.statusCode());
      assertEquals("application/json",response.contentType());
      assertTrue(response.body().asString().contains("Blythe"));

   }
   /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

   @Test
   public void test2(){

      Response response = given().accept(ContentType.JSON).
              and().pathParam("id", 500)
              .when().get("/api/spartans/{id}");

      assertEquals(404,response.statusCode());
      System.out.println("response.statusCode() = " + response.statusCode());
      assertEquals("application/json",response.contentType());
      System.out.println("response.contentType() = " + response.contentType());
      assertTrue(response.body().asString().contains("Not Found"));
      response.prettyPrint();
   }
}
