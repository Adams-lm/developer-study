package com.hznu.designMode.builder;

/**
 * @author LIN
 * @date 2022/3/10 10:18
 */
public class Coke extends ColdDrink{
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}
