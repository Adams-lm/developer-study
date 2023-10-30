package com.hznu.util.regex;

/**
 * @author LIN
 * @date 2022/12/27 10:50
 */
public class Method {
    public String handleString(String str){
        System.out.println("before");
        String result = str.substring(0, str.length() - 1);
        System.out.println("after");
        return result;
    }
}
