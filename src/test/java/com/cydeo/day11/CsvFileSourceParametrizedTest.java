package com.cydeo.day11;

import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CsvFileSourceParametrizedTest {
   @ParameterizedTest
   @CsvFileSource(resources = "/postalcode.csv", numLinesToSkip = 1)
   public void zipCodeTestWithFile(String state, String city, int zipCount) {
      System.out.println("state = " + state);
      System.out.println("city = " + city);
      System.out.println("zipCount = " + zipCount);

      Response response = given().baseUri("https://api.zippopotam.us")
              .pathParams("state", state, "city", city)
              .when().get("us/{state}/{city}")
              .then()
              .statusCode(200).extract().response();
      List<String> placeName = response.path("places.'place name'");
      assertThat(placeName, everyItem(containsStringIgnoringCase(city)));

      List<String> placeList = response.path("places");
      System.out.println("placeList.size() = " + placeList.size());
      assertThat(zipCount, equalTo(placeList.size()));
   }
}

