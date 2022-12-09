package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SpartanWithXML extends SpartanAuthTestBase {
  @DisplayName("GET request to /api/spartans and verify xml")
   @Test
   public void getSpartanXML(){
     given().accept(ContentType.XML).and().auth().basic("admin","admin")
             .when()
             .get("/api/spartans")
             .then()
             .statusCode(200)
             .contentType("application/xml;charset=UTF-8")
             .body("List.item[0].name",is("Meade"))
             .body("List.item[0].gender",is("Male"))
             .log().all();

   }
   @Test
   public void testXMLPath() {
      Response response = given().accept(ContentType.XML).and().auth().basic("admin", "admin")
              .when()
              .get("/api/spartans");
      XmlPath xmlPath = response.xmlPath();
      System.out.println(xmlPath.getInt("List.item[2].id"));
      List<String> names = xmlPath.getList("List.item.name");
      System.out.println("names = " + names);
   }
}
