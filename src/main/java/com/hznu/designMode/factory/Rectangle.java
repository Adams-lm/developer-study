package com.hznu.designMode.factory;

/**
 * @author LIN
 * @date 2022/3/9 23:32
 */
public class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("DesignMode.Factory.Rectangle draw");
    }
}
