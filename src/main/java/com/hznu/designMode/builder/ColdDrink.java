package com.hznu.designMode.builder;

/**
 * @author LIN
 * @date 2022/3/10 10:15
 */
public abstract class ColdDrink implements Item{

    @Override
    public Packing packing(){
        return new Bottle();
    }
}
