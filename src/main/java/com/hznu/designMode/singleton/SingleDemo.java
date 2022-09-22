package com.hznu.designMode.singleton;

/**
 * @author LIN
 * @date 2022/3/9 23:45
 */
public class SingleDemo {
    public static void main(String[] args) {
        SingleObject instance = SingleObject.getInstance();
        instance.showMessage();
    }
}
