package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class JSONToJavaTest extends SpartanTestBase {
@Test
   public void oneSpartanToMap(){

   Response response = given().pathParam("id", 15)
           .when()
           .get("api/spartans/{id}")
           .then().statusCode(200)
           .extract().response();

//get the json and convert it into map
   Map<String,Object> jsonMap = response.as(Map.class);

   System.out.println(jsonMap.toString());
}
@DisplayName("Get all spartans to Java data structure")
@Test
   public void getAllSpartan(){

   Response response = get("/api/spartans").then().statusCode(200).extract().response();
   List<Map<String,Object>> spartans = response.as(List.class);

   System.out.println(spartans.toString());
}


}
