package com.cydeo.day6;

import com.cydeo.pojo.Regions;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ORDSPojoGetRequestTest extends HRTestBase {
   @Test
   public void test5(){
      Regions region1 = given().accept(ContentType.JSON)
              .when().get("/regions")
              .then().statusCode(200)
              .extract().jsonPath().getObject("items[0]", Regions.class);

      System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
      System.out.println(region1.getLinks());

   }

}
