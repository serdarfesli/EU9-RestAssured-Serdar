package com.cydeo.day7;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import com.cydeo.utilities.SpartansUtil;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.Assertions.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequestDemo extends SpartanTestBase {
   /*
     Given accept type and Content type is JSON
     And request json body is:
     {
       "gender":"Male",
       "name":"Severus",
       "phone":8877445596
    }
     When user sends POST request to '/api/spartans'
     Then status code 201
     And content type should be application/json
     And json payload/response/body should contain:
     "A Spartan is Born!" message
     and same data what is posted
  */
   @Test
   public void postMethod1() {
      String requestJsonBody = "\"gender\":\"Male\",\"name\":\"James\",\"phone\":1324657789";
      Response response = given().accept(ContentType.JSON)
              .and()
              .contentType(ContentType.JSON)
              .and().body(requestJsonBody)
              .when().post("/api/spartans");

      assertThat(response.statusCode(), is(201));
      assertThat(response.contentType(), is("application/json"));
      assertThat(response.path("success"), is("A Spartan is Born!"));
      assertThat(response.path("data.name"), is("James"));
      assertThat(response.path("data.gender"), is("Male"));
      assertThat(response.path("data.phone"), is(1324657789L));

   }

   @Test
   @DisplayName("POST with Map to JSON")
   public void postMethod2() {
      Faker faker = new Faker();

      Map<String, Object> requestJsonBodyInMap = new LinkedHashMap<>();
      requestJsonBodyInMap.put("gender", "Male");
      requestJsonBodyInMap.put("name", "James");
      requestJsonBodyInMap.put("phone", 13246577891L);

      Response response = given().accept(ContentType.JSON)
              .and()
              .contentType(ContentType.JSON)
              .and().body(requestJsonBodyInMap)
              .when().post("/api/spartans");

      assertThat(response.statusCode(), is(201));
      assertThat(response.contentType(), is("application/json"));
      assertThat(response.path("success"), is("A Spartan is Born!"));
      assertThat(response.path("data.name"), is("James"));
      assertThat(response.path("data.gender"), is("Male"));
      assertThat(response.path("data.phone"), is(13246577891L));
   }

   @Test
   @DisplayName("POST with Map to JSON")
   public void postMethod4() {

      Map<String, Object> spartanMapObject = SpartansUtil.createSpartanMapObject();

      Response response = given().accept(ContentType.JSON)
              .and()
              .contentType(ContentType.JSON)
              .and().body(spartanMapObject)
              .when().post("/api/spartans");

      response.prettyPrint();

      assertThat(response.statusCode(), is(201));
      assertThat(response.contentType(), is("application/json"));
      assertThat(response.path("success"), is("A Spartan is Born!"));
      assertThat(response.path("data.name"), is("James"));
      assertThat(response.path("data.gender"), is("Male"));
      assertThat(response.path("data.phone"), is(13246577891L));
   }

   @Test
   @DisplayName("POST with POJO to JSON")
   public void postMethod3() {
      //create one Object from our pojo, send it as a JSON
      Faker faker = new Faker();

      Spartan spartan = new Spartan();
      spartan.setName("Serdar");
      spartan.setGender("Male");
      spartan.setPhone(13246577891L);
      System.out.println("spartan = " + spartan);
      String expectedResponseMessage="A Spartan is Born!";

      int idFromPost = given().accept(ContentType.JSON)
              .and()
              .contentType(ContentType.JSON)
              .and().body(spartan)
              .when().post("/api/spartans")
              .then()
              .statusCode(201)
              .contentType("application/json")
              .body("success",is(expectedResponseMessage))
              .extract().jsonPath().getInt("data.id");

      Spartan spartanPosted = given().accept(ContentType.JSON).and().pathParam("id", idFromPost)
              .when().get("/api/spartans/{id}").then().statusCode(200).extract().as(Spartan.class);

      assertThat(spartanPosted.getName(),is(spartan.getName()));
      assertThat(spartanPosted.getGender(),is(spartan.getGender()));
      assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));
      assertThat(spartanPosted.getId(),is(idFromPost));


   }
}
//Create one SpartanUtil class
//create a static method that returns Map<String,Object>
//use faker library(add as a depedency) to assign each time different information
//for name,gender,phone number
//then use your method for creating spartan as a map,dynamically.
