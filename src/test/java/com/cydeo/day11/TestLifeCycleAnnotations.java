package com.cydeo.day11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {

   @BeforeAll
   public static void init(){
      System.out.println("Before All is running");
   }

   @BeforeEach
   public void initEach(){
      System.out.println("Before each is running");
   }

   @Test
   public void test1(){
      System.out.println("test 1 is running");
   }

   @AfterEach
   public void initEach2(){
      System.out.println("After each is running");
   }
   @Test
   public void test2(){
      System.out.println("test 2 is running");
   }

   @AfterAll
   public static void close(){
      System.out.println("After all is running");
   }
}
