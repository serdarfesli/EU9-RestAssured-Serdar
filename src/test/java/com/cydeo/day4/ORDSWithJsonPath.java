package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
public class ORDSWithJsonPath extends HRTestBase {

@Test
   public void test1(){
   Response response = get("/countries");
   JsonPath jsonPath = response.jsonPath();
   System.out.println("jsonPath.getString(\"item[1].country_name\") = " + jsonPath.get("items[1].country_name"));
   List<String> list = jsonPath.getList("items.country_name");
   System.out.println("list = " + list);

   System.out.println("jsonPath.param(\"region_id\", 2).getString(\"country_name\") = " + jsonPath.param("region_id", 2).getString("country_name"));

}

@Test
   public void Test1(){
   Response response = given().queryParam("limit", 107)
           .when().get("/employees");

   JsonPath jsonPath = response.jsonPath();
   List<String> employeeEmails = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email"); //emails of the employess whose job_id is IT_PROG
   System.out.println("employeeEmails = " + employeeEmails);

   List<String> firstnames = jsonPath.getList("items.findAll {it.salary>10000}.first_name"); //first_names of the employees whose salary is more than 10000

   System.out.println("firstnames = " + firstnames);
}



}
