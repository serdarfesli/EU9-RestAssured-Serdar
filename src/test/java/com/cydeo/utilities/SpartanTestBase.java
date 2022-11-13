package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public abstract class SpartanTestBase {
   @BeforeAll
   public static void init() {
      baseURI = "http://44.208.34.43:8000";
   }
}
