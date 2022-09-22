package com.hznu.lambda;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LIN
 * @date 2022/9/19 11:04
 */
public class ListSort {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<Person>() {{
            add(new Person(30, "北京"));
            add(new Person(10, "西安"));
            add(new Person(40, "上海"));
            add(new Person(null, "上海"));
        }};
        // sort
        list = list.stream()
                .sorted(Comparator.comparing(Person::getAge,
                        Comparator.nullsLast(Integer::compareTo)))
                .collect(Collectors.toList());
        // sout
        list.forEach(System.out::println);
        System.out.println("asd"+"qwe");
    }

    @Data
    static class Person {
        private Integer age;
        private String name;

        public Person(Integer age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}
