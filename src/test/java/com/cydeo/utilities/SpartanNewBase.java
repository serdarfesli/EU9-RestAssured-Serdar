package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanNewBase {
    @BeforeAll
    public static void init() {

        baseURI = "http://44.208.34.43";
        port=7000;
        basePath="/api";

    }

    @AfterAll
    public static void close(){
        reset();
}

}
