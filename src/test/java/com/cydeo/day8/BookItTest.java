package com.cydeo.day8;

import com.cydeo.utilities.BookitUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BookItTest {
   @BeforeAll
   public static void init() {
      //save baseurl inside this variable so that we dont need to type each http method.
      baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";
   }

   //String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTUxNiIsImF1ZCI6InRlYWNoZXIifQ.saFcTsRyMJQj1e8jhya1zpxngBggh5fC3lGsGyBCrQs";

   @DisplayName("GET all campuses")
   @Test
   public void testAuth1() {
      given()
              .header("Authorization", BookitUtils.getAccessToken("teacher"))
              .accept(ContentType.JSON)
              .when()
              .get("/api/campuses")
              .then().statusCode(200).log().all();

   }
}
