package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class OldRestAssuredTest extends SpartanNewBase {
    @Test //this is current verison
    public void getAllSpartan(){
    given()
            .accept(ContentType.JSON)
            .auth().basic("admin","admin")
            .log().all()
            .when().get("/spartans")
            .then().statusCode(200)
            .body("id[0]",is(10))
            .body("id[5]",is(19));
    }


}
