package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class SpartanSpecTest extends SpartanNewBase {

//all tests in this class will use admin credentials
    //all test in this class will expect json result from response

    //all test in this class response status code is 200
    //all test in this class response content type header is json

    @Test
    public void test1() {

//         RequestSpecification requestSpec = given()
//                                             .accept(ContentType.JSON)
//                                             .and()
//                                              .auth().basic("admin", "admin")
//                                              .log().all();
//
//         ResponseSpecification responseSpec = expect().statusCode(200)
//                                              .and()
//                                             .contentType(ContentType.JSON)
//                                              .logDetail(LogDetail.ALL);  //logging with response specficiation

given().spec(requestSpec)
        .pathParam("id",15)
        .when().get("/spartans")
        .then().spec(responseSpec);

    }

    @Test
    public void test2() {



    }

    @Test
    public void test3() {



    }
}
