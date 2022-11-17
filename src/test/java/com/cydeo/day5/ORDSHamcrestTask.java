package com.cydeo.day5;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ORDSHamcrestTask extends HRTestBase {
   @Test
   public void employeesTest(){
given().accept(ContentType.JSON)
        .and()
        .queryParam("q","{\"job_id\":\"IT_PROG\"}")
        .when()
        .get("/employees")
        .then()
        .body("items.job_id",everyItem(is("IT_PROG")))
                .body("items.first_name",containsInAnyOrder("Bruce","Alexander","David","Valli","Diana"));





   }
}
