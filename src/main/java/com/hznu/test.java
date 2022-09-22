package com.hznu;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;


/**
 * @author LIN
 * @date 2022/4/17 11:48
 */
public class test {

    @Test
    public void timeTest() {
        List<String> sentences = Arrays.asList("hello world","Jia Gou Wu Dao");
        // 演示点1： 仅peek操作，最终不会执行
        System.out.println("----before peek----");
        sentences.stream().peek(sentence -> System.out.println(sentence));
        System.out.println("----after peek----");
        // 演示点2： 仅foreach操作，最终会执行
        System.out.println("----before foreach----");
        sentences.stream().forEach(sentence -> System.out.println(sentence));
        System.out.println("----after foreach----");
        // 演示点3：peek操作后面增加终止操作，peek会执行
        System.out.println("----before peek and count----");
        sentences.stream().peek(sentence -> System.out.println(sentence)).count();
        System.out.println("----after peek and count----");
        }
    }


