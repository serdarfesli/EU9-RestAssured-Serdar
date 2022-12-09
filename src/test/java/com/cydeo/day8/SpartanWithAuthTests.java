package com.cydeo.day8;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanWithAuthTests extends SpartanAuthTestBase {

   @Test
   public void testWithNoAuth() {
      get("/api/spartans").then().
              statusCode(401).log().all();
   }

   @DisplayName("Get api/spartans as admin user expect 200")
   @Test
   public void testWithAuth() {
      given().auth().basic("admin", "admin").
              get("/api/spartans").then().
              statusCode(200).log().all();
   }

   @DisplayName("DELETE /spartans{id} as editor expect 403")
   @Test
   public void deleteAsEditor() {
      given().auth().basic("editor", "editor").and()
              .accept(ContentType.JSON)
              .pathParam("id", 10).
              when().
              delete("/api/spartans/{id}").then().
              statusCode(402).log().all();
   }

   @Test
   public void AdminCRUDTest() {
//Admin should create and read
      Spartan spartan1 = new Spartan();
      spartan1.setName("Serdar");
      spartan1.setGender("Male");
      spartan1.setPhone(1234567894L);
      int idPosted = given()
              .contentType(ContentType.JSON)
              .auth().basic("admin", "admin").body(spartan1)
              .when().post("/api/spartans")    //admin creates a spartan
              .then().statusCode(201).log().all().extract().jsonPath().getInt("data.id");
      System.out.println("idPosted = " + idPosted);

      Spartan spartanPosted = given().accept(ContentType.JSON)
              .auth().basic("admin","admin")
              .pathParam("id", idPosted)
              .when()
              .get("/api/spartans/{id}")      //we try to retrieve the spartan that we just created
              .then().statusCode(200)
              .and().extract().as(Spartan.class);


      assertThat(spartanPosted.getName(),is(spartan1.getName()));
      assertThat(spartanPosted.getGender(),is(spartan1.getGender()));
      assertThat(spartanPosted.getPhone(),is(spartan1.getPhone()));
      //---> test passed, we verified that admin can create Spartan

//Admin should update
      Map<String,Object> updatedName= new HashMap<>();
      updatedName.put("name","SSSSSerdar");
      given().contentType(ContentType.JSON).auth().basic("admin","admin")
              .pathParam("id",idPosted).body(updatedName) //update only name
              .when()
              .patch("/api/spartans/{id}") //partial updating SPartan
              .then().log().all().statusCode(204);

      Response response = given().accept(ContentType.JSON)
              .auth().basic("admin", "admin")
              .pathParam("id", idPosted)
              .when()
              .get("/api/spartans/{id}")
              .then().statusCode(200).extract().response();

      System.out.println("response.path(\"name\") = " + response.path("name")); //name is verified, admin can update is verified

      given().accept(ContentType.JSON)
              .auth().basic("admin", "admin")
              .pathParam("id", idPosted)
              .when()
              .delete("/api/spartans/{id}") // admin deletes
              .then().statusCode(204);

      Response responseafterDelete = given().accept(ContentType.JSON)
              .auth().basic("admin", "admin")
              .pathParam("id", idPosted)
              .when()
              .get("/api/spartans/{id}")
              .then().statusCode(404).extract().response(); // it is verified that admin can delete--> we tried to retrieve the name but
                                                                              // it is deleted so it returns 404
   }

   @Test
   public void EditorCRUDTest() {
//Editor should create and read
      Spartan spartan1 = new Spartan();
      spartan1.setName("Serdar");
      spartan1.setGender("Male");
      spartan1.setPhone(1234567894L);
      int idPosted = given()
              .contentType(ContentType.JSON)
              .auth().basic("editor", "editor").body(spartan1)
              .when().post("/api/spartans")    //editor creates a spartan
              .then().statusCode(201).log().all().extract().jsonPath().getInt("data.id");
      System.out.println("idPosted = " + idPosted);

      Spartan spartanPosted = given().accept(ContentType.JSON)
              .auth().basic("editor","editor")
              .pathParam("id", idPosted)
              .when()
              .get("/api/spartans/{id}")      //we try to retrieve the spartan that we just created
              .then().statusCode(200)
              .and().extract().as(Spartan.class);


      assertThat(spartanPosted.getName(),is(spartan1.getName()));
      assertThat(spartanPosted.getGender(),is(spartan1.getGender()));
      assertThat(spartanPosted.getPhone(),is(spartan1.getPhone()));
      //---> test passed, we verified that editor can create Spartan

//editor should update
      Map<String,Object> updatedName= new HashMap<>();
      updatedName.put("name","SSSSSerdar");
      given().contentType(ContentType.JSON).auth().basic("editor","editor")
              .pathParam("id",idPosted).body(updatedName) //update only name
              .when()
              .patch("/api/spartans/{id}") //partial updating SPartan
              .then().log().all().statusCode(204);

      Response response = given().accept(ContentType.JSON)
              .auth().basic("editor", "editor")
              .pathParam("id", idPosted)
              .when()
              .get("/api/spartans/{id}")
              .then().statusCode(200).extract().response();

      System.out.println("response.path(\"name\") = " + response.path("name")); //name is verified, editor can update is verified

      given().accept(ContentType.JSON)
              .auth().basic("editor", "editor")
              .pathParam("id", idPosted)
              .when()
              .delete("/api/spartans/{id}") // editor tries deleting
              .then().statusCode(403); //we try to verify that editor cannot delete-> expected ->forbidden error

      Response responseafterDelete = given().accept(ContentType.JSON)
              .auth().basic("editor", "editor")
              .pathParam("id", idPosted)
              .when()
              .get("/api/spartans/{id}")
              .then().statusCode(200).extract().response(); // it is verified that editor cannot delete--> we  retrieved the spartan that
                                                                              // means it is not deleted

   }
}
/*
        As a homework,write a detealied test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD   ✅
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all

     */
