package com.cydeo.utilities;


import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class HRTestBase {
   @BeforeAll
   public static void init() {
      baseURI = "http://44.208.34.43:1000/ords/hr";

   }
}
