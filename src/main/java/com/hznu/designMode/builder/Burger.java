package com.hznu.designMode.builder;

/**
 * @author LIN
 * @date 2022/3/10 0:06
 */
public abstract class Burger implements Item{

    @Override
    public Packing packing(){
        return new Wrapper();
    }

}
