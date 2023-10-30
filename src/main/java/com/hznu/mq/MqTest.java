package com.hznu.mq;

import org.junit.Test;


/**
 * @author LIN
 * @date 2022/4/17 11:48
 */
public class MqTest {

    @Test
    public void timeTest() {
        aa();
    }

    public static void aa(Object... params){
        Integer a = 1;
        System.out.println(a == null);
    }



}


