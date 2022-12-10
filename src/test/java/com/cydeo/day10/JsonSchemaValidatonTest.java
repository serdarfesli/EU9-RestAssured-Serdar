package com.cydeo.day10;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class JsonSchemaValidatonTest extends SpartanAuthTestBase {

   @DisplayName("GET request to verify one spartan against to schema")
   @Test
   public void schemaValidation(){
   given().accept(ContentType.JSON)
           .pathParam("id",10)
           .auth().basic("admin","admin")
           .when().get("/api/spartans/{id}")
           .then()
           .statusCode(200).body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));

   }
@DisplayName("GET request to all spartans and verify schema")
   @Test
   public void allSpartanSchemaTest(){
given().accept(ContentType.JSON)
        .auth().basic("admin","admin")
        .when().get("/api/spartans").then().statusCode(200)
        .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/allSpartansSchema.json")));
   }

   //homework
   //put your post json schema under day10
   //post one spartan using dynamic input(name,gender,phone)
   //verify your post response matching with json schema

   @Test
   public void postSchemaTesthomework(){
      Spartan spartan10 = new Spartan();
      spartan10.setName("Ally");
      spartan10.setGender("Male");
      spartan10.setPhone(1236549877L);
      given().contentType(ContentType.JSON).and().accept(ContentType.JSON)
              .body(spartan10)
              .auth().basic("admin","admin")
              .when().post("/api/spartans")
              .then()
              .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/spartanPostJsonSchema.json")));


   }
}
