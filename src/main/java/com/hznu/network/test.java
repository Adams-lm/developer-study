package com.hznu.network;

import org.junit.Test;

import java.net.URL;

/**
 * @author LIN
 * @date 2022/9/23 17:02
 */
public class test {
    @Test
    public  void main() {
        URL resource = this.getClass().getClassLoader().getResource("beauty.jpg");
        System.out.println(resource.getPath());
    }
}
