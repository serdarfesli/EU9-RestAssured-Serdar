package com.cydeo.day5;

import com.cydeo.utilities.DBUtils;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import java.util.Map;

public class SpartanAPIvsDB extends SpartanTestBase {

   @DisplayName("Get one spartan from api and db")
   @Test
   public  void testDB1() {
      //get id,name,gender phone  from database
      Map<String, Object> mapFromDB = DBUtils.getRowMap("select SPARTAN_ID,NAME,GENDER,PHONE from SPARTANS where SPARTAN_ID=15");
      System.out.println(mapFromDB);
      //get same information from api
      Map<String,Object> mapFromAPI = given().accept(ContentType.JSON)
              .pathParam("id", 15)
              .when()
              .get("api/spartans/{id}")
              .then()
              .statusCode(200)
              .contentType("application/json")
              .extract().response().as(Map.class);

      System.out.println(mapFromAPI);

      //compare database VS Api ---
assertThat(  mapFromAPI.get("id").toString(), is ( mapFromDB.get("SPARTAN_ID").toString()));
assertThat( mapFromAPI.get("name"),is( mapFromDB.get("NAME")));
assertThat( mapFromAPI.get("gender"),is( mapFromDB.get("GENDER")));
assertThat( mapFromAPI.get("phone").toString(),is( mapFromDB.get("PHONE").toString()));
   }

}
