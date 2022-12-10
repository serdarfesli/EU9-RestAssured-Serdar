package com.cydeo.day10;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SSLTest {
   @Test
   public void test1(){
      given()
              .relaxedHTTPSValidation()
              .when().get("https://untrusted-root.badssl.com/").prettyPrint();

   }

}
