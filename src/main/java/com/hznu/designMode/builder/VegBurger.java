package com.hznu.designMode.builder;

/**
 * @author LIN
 * @date 2022/3/10 10:16
 */
public class VegBurger extends Burger{
    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}
