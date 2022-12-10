package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
}
