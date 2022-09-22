package com.hznu.designMode.builder;

/**
 * @author LIN
 * @date 2022/3/10 10:19
 */
public class Pepsi extends ColdDrink{
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 35.0f;
    }
}
