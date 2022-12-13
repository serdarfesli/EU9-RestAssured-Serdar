package com.cydeo.utilities;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanNewBase {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeAll
    public static void init() {

        baseURI = "http://44.208.34.43";
        port=7000;
        basePath="/api";

        requestSpec = given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all();

        responseSpec = expect().statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);  //logging with response specficiation
    }

    @AfterAll
    public static void close(){
        reset();
}

}
