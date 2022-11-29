package com.cydeo.day7;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class PutAndPatchRequestDemo extends SpartanTestBase {
   @DisplayName("Put request to one Spartan for update with Map")
   @Test
   public void PUTRequest(){
      Map<String,Object> putRequestMap = new HashMap<>();
      putRequestMap.put("name","JamesHHH");
      putRequestMap.put("gender","Female");
      putRequestMap.put("phone",111111111111L);

      given().accept(ContentType.JSON).and().contentType(ContentType.JSON)
              .pathParam("id", 110).and().body(putRequestMap)
              .when().put("/api/spartans/{id}").then().statusCode(204);

      Spartan spartanUpdated = given().accept(ContentType.JSON).pathParam("id", 110).when()
              .get("/api/spartans/{id}").then().extract().as(Spartan.class);

      assertThat(spartanUpdated.getName(),is(putRequestMap.get("name")));
      assertThat(spartanUpdated.getGender(),is(putRequestMap.get("gender")));
      assertThat(spartanUpdated.getPhone(),is(putRequestMap.get("phone")));
   }

   @DisplayName("PATCH request to one Spartan for update with Map")
   @Test
   public void PATCHRequest(){
      Map<String,Object> putRequestMap = new HashMap<>();
      putRequestMap.put("name","JameswithPATCH");


      given().accept(ContentType.JSON).and().contentType(ContentType.JSON)
              .pathParam("id", 110).and().body(putRequestMap)
              .when().patch("/api/spartans/{id}").then().statusCode(204);

      Spartan spartanUpdatedWithPatch = given().accept(ContentType.JSON).pathParam("id", 110).when()
              .get("/api/spartans/{id}").then().log().all().extract().as(Spartan.class);

      assertThat(spartanUpdatedWithPatch.getName(),is(putRequestMap.get("name")));
      System.out.println("spartanUpdatedWithPatch.getName() = " + spartanUpdatedWithPatch.getName());

   }




}
