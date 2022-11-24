package com.cydeo.day6;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
public class ORDSPojoGetRequestTest extends HRTestBase {
   @Test
   public void test5(){
      Region region1 = given().accept(ContentType.JSON)
              .when().get("/regions")
              .then().statusCode(200)
              .extract().jsonPath().getObject("items[0]", Region.class);

      System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
      System.out.println(region1.getLinks());

   }

   @Test
   public void employeeGet(){
      Employee employee = get("/employees").then().statusCode(200)
              .extract().jsonPath().getObject("items[0]", Employee.class);

      System.out.println(employee);
      System.out.println(employee.getFirstName());
   }
   /* send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non used fields
   */

@Test
   public void test6(){

   JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

   List<Region> regionsList = jsonPath.getList("items",Region.class);

   List<Integer> regionIds=new ArrayList<>();
   List<Integer> expectedRegionIds=Arrays.asList(1,2,3,4);

      List<String> regionNames=new ArrayList<>();
      List<String> expectedRegionNames= Arrays.asList("Europe","Americas","Asia","Middle East and Africa");

   for (Region eachregion : regionsList) {
      regionIds.add(eachregion.getRegion_id());
      regionNames.add(eachregion.getRegion_name());
   }
assertThat(regionIds,is(expectedRegionIds));
assertThat(regionNames,is(expectedRegionNames));

}
}

