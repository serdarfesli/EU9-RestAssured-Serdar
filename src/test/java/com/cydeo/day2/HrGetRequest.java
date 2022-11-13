package com.cydeo.day2;

import static io.restassured.RestAssured.*;

import com.cydeo.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HrGetRequest extends HRTestBase {


@Test
   public void test1(){
   Response response = get("/regions");
   System.out.println("response.statusCode() = " + response.statusCode());

}

@Test
   public void test2(){
   Response response = given().accept(ContentType.JSON).
           when().get("/regions/2");

   assertEquals(200,response.statusCode());
   assertEquals("application/json",response.contentType());
   assertTrue(response.body().asString().contains("Americas"));


}
}
