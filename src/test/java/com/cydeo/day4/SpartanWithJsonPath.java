package com.cydeo.day4;
import groovy.json.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import com.cydeo.utilities.SpartanTestBase;
import org.junit.jupiter.api.Test;

public class SpartanWithJsonPath extends SpartanTestBase {

@Test
public void test1(){
   Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
           .when().get("http://44.208.34.43:1000/ords/hr/employees");
   JsonPath jsonPath = response.jsonPath();


   System.out.println("response.path(\"items.first_name\") = " + response.path("items.first_name"));

}



}
