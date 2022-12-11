package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Map;

public class BookitParametrized {

   @ParameterizedTest
   @MethodSource("getExcelData")
   public void bookItBearTokenProvideValidation(Map<String, String> userInfo) {
      System.out.println("userInfo.get(\"email\") = " + userInfo.get("email"));
      System.out.println("userInfo.get(\"password\") = " + userInfo.get("password"));

      given().accept(ContentType.JSON).baseUri("https://cybertek-reservation-api-qa.herokuapp.com")
        .queryParams("email",userInfo.get("email"),"password",userInfo.get("password"))
        .when().get("/sign")
        .then()
        .statusCode(200);

   }


   public static List<Map<String, String>> getExcelData() {
      ExcelUtil bookit = new ExcelUtil("src/test/resources/BookItQa3.xlsx", "QA3");

      return bookit.getDataList();
   }
}