package com.cydeo.day5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class HamcrestMatchersIntro {


   @Test
   public void simpleTest1(){
assertThat(5+5,is(lessThanOrEqualTo(10)));

      List<Integer> list = new ArrayList<>(Arrays.asList(10,20,30,30,20,20,20));
      assertThat(list,hasSize(7));
      assertThat(list,hasItem(20));



   }


}
