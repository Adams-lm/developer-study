package com.hznu.util;

import java.util.HashMap;

/**
 * @author LIN
 * @date 2023/2/13 15:40
 */
public class TheReplacements {
    public static void main(String[] args) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("11","QQ");
        map.put("2","WECHAT");

        String str = "{2}.reqBody.balCeil>0";

        StringBuilder sb = new StringBuilder();
        int left = 0;
        int strLength = str.length();
        while (true) {
            // 1、找到第一个‘{’，其余字符串直接拼接
            while (left < strLength && str.charAt(left) !='{'){
                sb.append(str.charAt(left));
                left++;
            }
            if (left >= strLength){
                break;
            }
            // 2、找到对应的‘}’
            int right = left + 1;
            while(right < strLength && str.charAt(right) != '}'){
                right++;
            }
            // 3、截取nodeId
            String nodeId = str.substring(left + 1, right);
            if (!map.containsKey(nodeId)){
                System.out.println("结点不存在！");
                return;
            }
            // 4、{nodeId} --> nodeName
            sb.append(map.get(nodeId));
            left = right + 1;
            while (true){
                if (left >= strLength){
                    break;
                }
                if (str.charAt(left)=='<'||str.charAt(left)=='>'||str.charAt(left)=='='){
                    break;
                }
                // 5.获取单词left,其余字符('.', '<>=', '数字')
                if (left < strLength && !Character.isLetter(str.charAt(left))){
                    sb.append(str.charAt(left));
                    left++;
                }
                if (left >= strLength){
                    break;
                }
                right = left + 1;
                // 6.获取单词right
                while(right < strLength && Character.isLetter(str.charAt(right))){
                    right++;
                }
                if (right >= strLength){
                    break;
                }
                String attrCode = str.substring(left, right);
                // 7、处理attributeCode
                System.out.println(attrCode);
                sb.append(attrCode);
                left = right;
            }

        }
        String expression = sb.toString();
        System.out.println(expression);
    }
}
