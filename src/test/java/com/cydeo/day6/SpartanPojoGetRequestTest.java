package com.cydeo.day6;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {


   @DisplayName("get one spartan and convert it to spartan object")
   @Test
   public void oneSpartanPojo() {

      Response response = given().accept(ContentType.JSON)
              .pathParam("id", 15)
              .when().get("api/spartans/{id}")
              .then().statusCode(200)
              .extract().response();

      Spartan spartan15 = response.as(Spartan.class);

      System.out.println("spartan15 = " + spartan15);
      System.out.println("spartan15.getName() = " + spartan15.getName());
      System.out.println("spartan15.getPhone() = " + spartan15.getPhone());
   }

   @Test
   public void spartanSearchWithPojo() {
      JsonPath jsonPath = given().accept(ContentType.JSON)
              .and().queryParams("nameContains", "a", "gender", "Male")
              .when().get("/api/spartans/search")
              .then().statusCode(200)
              .extract().jsonPath();

      Spartan s1 = jsonPath.getObject("content[0]", Spartan.class);
      System.out.println("s1.getName() = " + s1.getName());
      System.out.println("s1 = " + s1);

   }
   @Test
   public void test3(){
      Response response = given().accept(ContentType.JSON)
              .and().queryParams("nameContains", "a",
                      "gender", "Male")
              .when().get("/api/spartans/search")
              .then().statusCode(200)
              .extract().response();

      Search searchResult = response.as(Search.class);

      System.out.println(searchResult.getContent().get(0).getName());
   }

   @DisplayName("Get /spartans/search and save as List<Spartan>")
@Test
   public void test4(){
      List<Spartan> spartanList = given().accept(ContentType.JSON)
              .and().queryParams("nameContains", "a",
                      "gender", "Male")
              .when().get("/api/spartans/search")
              .then().statusCode(200)
              .extract().jsonPath().getList("content",Spartan.class);
      System.out.println("spartanList.get(1).getName() = " + spartanList.get(1).getName());
   }


   }

