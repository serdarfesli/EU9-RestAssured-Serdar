package com.cydeo.day10;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class GovXmlTest {
   @Test
   public void test1() {
      //send a get request to
      //https://data.ct.gov/api/views/qm34-pq7e/rows.xml
      //get all the years
      //get all unknowns
      //get 2005 other
      //get 2007 _address

      XmlPath xmlPath = given().accept(ContentType.XML).when().get("https://data.ct.gov/api/views/qm34-pq7e/rows.xml").then().extract().xmlPath();

   }
}
