package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanWithXML extends SpartanAuthTestBase {
  @DisplayName("GET request to /api/spartans and verify xml")
   @Test
   public void getSpartanXML(){
     given().accept(ContentType.XML).and().auth().basic("admin","admin")
             .when()
             .get("/api/spartans")
             .then()
             .statusCode(200)
             .contentType("application/xml;charset=UTF-8")
             .body("List.item[0].name",is("Meade"))
             .body("List.item[0].gender",is("Male"))
             .log().all();

   }
}
