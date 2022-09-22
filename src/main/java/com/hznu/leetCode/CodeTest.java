package com.hznu.leetCode;

import java.util.*;

/**
 * @author LIN
 * @date 2022/3/11 10:13
 */
public class CodeTest {
    public static void main(String[] args) {
        String[] arrStr = {"Java", "C++", "Php", "C#", "Python", "C++", "Java"};
        test1(arrStr);
        test2(arrStr);
        test3(arrStr);
    }

    //方法1：通过List去重
    public static void test1(String[] arrStr) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arrStr.length; i++) {
            if (!list.contains(arrStr[i])) {
                list.add(arrStr[i]);
            }
        }
        System.out.println(list);

        //返回一个包含所有对象的指定类型的数组
        //String[] newArrStr =  list.toArray(new String[1]);
        //System.out.println(Arrays.toString(newArrStr));
    }

    //方法2：通过Map去重
    public static void test2(String[] arrStr) {
        Map<String, Object> map = new HashMap<>();
        for (String str : arrStr) {
            map.put(str, str);
        }
        System.out.println(map.keySet());
    }

    //方法3：通过Set去重
    public static void test3(String[] arrStr) {
        Set<String> set = new HashSet<>();
        for (String str : arrStr) {
            set.add(str);
        }
        System.out.println(set);
    }


}
