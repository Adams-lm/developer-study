package com.hznu.designMode.singleton;

/**
 * @author LIN
 * @date 2022/3/9 23:43
 */
public class SingleObject {
    private static SingleObject instance;
    private SingleObject(){};
    public static synchronized SingleObject getInstance(){
        if (instance == null){
            instance = new SingleObject();
        }
        return instance;
    }
    public void showMessage(){
        System.out.println("Single Message");
    }
}
