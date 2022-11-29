package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanAuthTestBase {
   @BeforeAll
   public static void init() {
      baseURI = "http://44.208.34.43:7000";

      String dbUrl = "jdbc:oracle:thin:@44.208.34.43:1521:XE";
      String dbUsername = "SP";
      String dbPassword = "SP";

      //  DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
   }

   @AfterAll
   public static void tearDown(){
      //  DBUtils.destroy();
   }
}
