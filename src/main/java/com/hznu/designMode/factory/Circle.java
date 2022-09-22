package com.hznu.designMode.factory;

/**
 * @author LIN
 * @date 2022/3/9 23:33
 */
public class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("DesignMode.Factory.Circle draw");
    }
}
