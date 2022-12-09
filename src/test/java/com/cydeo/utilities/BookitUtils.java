package com.cydeo.utilities;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;


public class BookitUtils {
   @BeforeAll
   public static void init() {
      //save baseurl inside this variable so that we dont need to type each http method.
      baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";
   }

   public static String getAccessToken(String role) {
      String accessToken =null;
      switch (role) {
         case "teacher":
             accessToken = "Bearer " + given().accept(ContentType.JSON)
                    .and()
                    .queryParams("email", "blyst6@si.edu", "password", "barbabaslyst")
                    .when().get("/sign").then().statusCode(200).extract().jsonPath().getString("accessToken");
         break;

         case "member":
            accessToken = "Bearer " + given().accept(ContentType.JSON)
                    .and()
                    .queryParams("email", "raymond@cydeo.com", "password", "abs123")
                    .when().get("/sign").then().statusCode(200).extract().jsonPath().getString("accessToken");
         break;

         case "leader":
            accessToken = "Bearer " + given().accept(ContentType.JSON)
                    .and()
                    .queryParams("email", "lfinnisz@yolasite.com", "password", "lissiefinnis")
                    .when().get("/sign").then().statusCode(200).extract().jsonPath().getString("accessToken");
         break;
      }
      return accessToken;
   }

}
