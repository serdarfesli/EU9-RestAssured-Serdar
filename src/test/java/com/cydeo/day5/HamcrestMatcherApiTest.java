package com.cydeo.day5;
import io.restassured.http.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class HamcrestMatcherApiTest {

   @Test
   public void test1(){

      given().accept(ContentType.JSON)
              .and()
              .pathParam("id",5)
              .when()
              .get("https://api.training.cydeo.com/teacher/{id}")
              .then()
              .statusCode(200)
              .contentType("application/json;charset=UTF-8")
              .header("transfer-encoding","chunked")
              .header("date",(notNullValue()))
              .and()
              .body("teachers[0].firstName",is("Mario"))
              .body("teachers[0].lastName",is("Luigi"))
              .body("teachers[0].gender",is("Male"));


   }

   @Test
   public void teachersTest(){


   }

}
