package com.cydeo.day4;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import com.cydeo.day2.HrGetRequest;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ORDSApiTestWithPath extends HRTestBase {

   @Test
   public void test1(){
      Response response= given().accept(ContentType.JSON)
              .queryParam("q","{\"region_id\":2}")
              .when()
              .get("/countries");
      System.out.println(response.path("limit").toString());
      System.out.println("response.path(\"items[2].links[0].href\") = " + response.path("items[2].links[0].href"));
      List<String> countries = response.path("items.country_name");
      System.out.println(countries);

      List<Integer> regionID = response.path("items.region_id");
      for (Integer each : regionID) {
         Assertions.assertTrue(each==2);
      }

   }
}
