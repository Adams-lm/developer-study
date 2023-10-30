package com.hznu;


/**
 * @author LIN
 * @date 2022/4/17 11:48
 */
public class DemoTest {

    @org.junit.Test
    public void timeTest() {
        aa();
    }

    public static void aa(Object... params){
        Integer a = 1;
        System.out.println(a == null);
    }

}


