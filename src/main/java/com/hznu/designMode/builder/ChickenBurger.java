package com.hznu.designMode.builder;

/**
 * @author LIN
 * @date 2022/3/10 10:18
 */
public class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 50.5f;
    }
}
