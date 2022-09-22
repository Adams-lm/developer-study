package com.hznu.leetCode;

/**
 * @author LIN
 * @date 2022/3/26 12:49
 */
public class B {
    public static B t1 = new B();
    public static B t2 = new B();

    {
        System.out.println("构造块");
    }

    static {
        System.out.println("静态块");
    }

    public static void main(String[] args) {
        B t = new B();
    }
}

