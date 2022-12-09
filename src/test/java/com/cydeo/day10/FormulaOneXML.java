package com.cydeo.day10;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class FormulaOneXML {
   @BeforeAll
   public static void setup(){
      //http://ergast.com/api/f1/drivers/alonso
      baseURI="http://ergast.com";
      basePath="/api/f1";
   }
   @Test
   public void test1(){

      Response response = given().pathParam("driver", "alonso")
              .when()
              .get("/drivers/{driver}")
              .then().statusCode(200).log().all().extract().response();

      XmlPath xmlPath = response.xmlPath();
      System.out.println(xmlPath.getString("MRData.DriverTable.Driver.GivenName"));
      System.out.println(xmlPath.getString("MRData.DriverTable.Driver.FamilyName"));

      //how to reach to the attributes in XML  -> we use @ sign
      System.out.println(xmlPath.getString("MRData.DriverTable.Driver.@driverId"));
      System.out.println(xmlPath.getString("MRData.DriverTable.Driver.@code"));
      System.out.println(xmlPath.getString("MRData.DriverTable.Driver.@url"));

   }
}
