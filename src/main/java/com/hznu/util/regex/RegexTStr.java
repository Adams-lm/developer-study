package com.hznu.util.regex;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LIN
 * @date 2022/12/14 20:23
 */
public class RegexTStr {
    public static void main(String[] args) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("11","QQ");
        map.put("2","WECHAT");


        System.out.println(StringUtils.uncapitalize("QweQQ"));
        String str1 = "{2}.2= {11}.Id";

        Method method = new Method();
        String s = method.handleString(str1);

        String[] splitStr = str1.split("\\.");
        StringJoiner stringJoiner = new StringJoiner(".");
        for (int i = 0; i < splitStr.length; i++) {
            if (i != 0){
                stringJoiner.add(splitStr[i].toLowerCase());
            }else{
                stringJoiner.add(splitStr[i]);
            }
        }
        String expression = stringJoiner.toString();
        System.out.println(stringJoiner);

        Pattern p= Pattern.compile("\\{(.+?)\\}");
        Matcher m=p.matcher(expression);


        System.out.println();
        StringBuffer sb=new StringBuffer();
        while(m.find()){
            String matchNo = m.group();
            String nodeNo = matchNo.substring(1, matchNo.length() - 1);
            System.out.println(nodeNo);
            String nodeName = map.get(nodeNo);
            if (nodeName != null){
                m.appendReplacement(sb, nodeName);
            } else {
                throw new Exception("结点" + matchNo + "不存在");
            }
        }
        m.appendTail(sb);
        System.out.println(sb);
    }
}
