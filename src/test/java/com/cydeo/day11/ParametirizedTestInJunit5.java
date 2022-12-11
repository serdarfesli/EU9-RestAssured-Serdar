package com.cydeo.day11;

import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ParametirizedTestInJunit5 {

   @ParameterizedTest
   @ValueSource(ints ={1,2,2,2,3,3,4,5})
   public void testByNumbers(int num){
      System.out.println("num = " + num);
      Assertions.assertTrue(num>3);
   }
   // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
   // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
   // check status code 200

@ParameterizedTest
   @ValueSource(ints = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
   public void zipCodeTest(int num){
      given()
              .accept(ContentType.JSON)
              .pathParam("zipcode",num)
              .when().get("https://api.zippopotam.us/us/{zipcode}")
              .then()
              .statusCode(200);

}



}
