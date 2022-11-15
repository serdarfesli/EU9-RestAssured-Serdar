package com.cydeo.day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

public class CBTrainingApiWithJsonPath {
   @BeforeAll
   public static void init() {
      baseURI = "https://api.training.cydeo.com";
   }

   @Test
   public void test1(){
      //send a get request to student id 23401 as a path parameter and accept header application/json
      //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
      //verify Date header exists
      //assert that
            /*
                firstName Bill
                batch 13
                section 100000
                emailAddress bill@email.com
                companyName Cydeo
                state VA
                zipCode 345345

                using JsonPath
             */
      Response response = given().pathParam("id",5).and().accept(ContentType.JSON)
              .when().get("/student/{id}");

      JsonPath jsonPath = response.jsonPath();

      assertEquals(200,response.statusCode());
      assertEquals("application/json;charset=UTF-8",response.contentType());
      assertEquals("chunked",response.header("transfer-encoding"));
assertTrue(response.headers().hasHeaderWithName("date"));

assertEquals("Bill",jsonPath.getString("students[0].firstName"));
assertEquals("13",jsonPath.getString("students[0].batch"));
assertEquals("100000",jsonPath.getString("students[0].section"));
assertEquals("bill@email.com",jsonPath.getString("students[0].contact.emailAddress"));
assertEquals("Cydeo",jsonPath.getString("students[0].company.companyName"));
assertEquals("VA",jsonPath.getString("students[0].company.address.state"));
assertEquals("345345",jsonPath.getString("students[0].company.address.zipCode"));

   }
}
