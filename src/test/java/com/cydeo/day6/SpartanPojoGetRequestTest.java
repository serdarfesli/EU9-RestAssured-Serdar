package com.cydeo.day6;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {


   @DisplayName("get one spartan and convert it to spartan object")
   @Test
   public void oneSpartanPojo(){

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




}
