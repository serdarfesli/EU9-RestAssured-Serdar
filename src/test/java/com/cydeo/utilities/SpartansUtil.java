package com.cydeo.utilities;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartansUtil {
   public static Map<String,Object> createSpartanMapObject(){
      Faker faker = new Faker();
      Map<String,Object> spartanMap = new LinkedHashMap<>();
      spartanMap.put("name",faker.name().firstName());
      spartanMap.put("gender","Male");
      spartanMap.put("phone",faker.phoneNumber().cellPhone());

      return spartanMap;
   }
}
//Create one SpartanUtil class
//create a static method that returns Map<String,Object>
//use faker library(add as a depedency) to assign each time different information
//for name,gender,phone number
//then use your method for creating spartan as a map,dynamically.
