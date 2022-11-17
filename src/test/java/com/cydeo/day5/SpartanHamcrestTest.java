package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class SpartanHamcrestTest extends SpartanTestBase {

   @Test
   public void test1(){
      List<String> names = given().accept(ContentType.JSON)
              .and()
              .queryParams("nameContains", "j", "gender", "Male")
              .when()
              .get("api/spartans/search")
              .then()
              .statusCode(200)
              .and()
              .body("totalElement", greaterThanOrEqualTo(3))
              .extract().response().jsonPath().getList("content.name");

   }

}
