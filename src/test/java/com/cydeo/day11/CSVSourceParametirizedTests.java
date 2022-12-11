package com.cydeo.day11;

import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class CSVSourceParametirizedTests {
// Test first number + second number = third number
//            1, 3 , 4
//            2, 3 , 5
//            8, 7 , 15
//            10, 9 , 19

   @ParameterizedTest
   @CsvSource({"1, 3 , 4","2, 3 , 5","8, 7 , 15","10, 9 , 19"})
   public void testWithCSVSource(int num1,int num2,int sum){
      assertThat(num1+num2,equalTo(sum));
   }

   // Write a parameterized test for this request
   // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"
     */
   //verify place name contains your city name
   //print number of places for each request

   @ParameterizedTest
   @CsvSource({"NY, New York",
           "CO, Denver",
           "VA, Fairfax",
           "VA, Arlington",
           "MA, Boston",
           "NY, New York",
           "MD, Annapolis"})
   public void testWithCSVSource2(String state,String city){

      Response response = given().baseUri("https://api.zippopotam.us")
              .pathParams("state", state, "city", city)
              .when().get("us/{state}/{city}")
              .then()
              .statusCode(200).extract().response();
      List<String> placeName= response.path("places.'place name'");
         assertThat(placeName,everyItem(containsStringIgnoringCase(city)));

       List<String> placeList= response.path("places");
      System.out.println("placeList.size() = " + placeList.size());
   }
   }

