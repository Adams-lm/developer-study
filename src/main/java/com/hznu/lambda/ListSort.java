package com.hznu.lambda;

import com.hznu.lambda.entity.Employee;
import lombok.Data;

/**
 * @author LIN
 * @date 2022/9/19 11:04
 */
public class ListSort {

    public static void main(String[] args) {
        // List<Person> list = new ArrayList<Person>() {{
        //     add(new Person(30, "北京"));
        //     add(new Person(10, "西安"));
        //     add(new Person(40, "上海"));
        //     add(new Person(null, "上海"));
        // }};
        // // sort
        // list = list.stream()
        //         .sorted(Comparator.comparing(Person::getAge,
        //                 Comparator.nullsLast(Integer::compareTo)))
        //         .collect(Collectors.toList());
        // // sout
        // list.forEach(System.out::println);
        // System.out.println("asd"+"qwe");
        // ArrayList<Person> people = new ArrayList<>();
        // List<Person> collect = people.stream().sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList());
        // System.out.println(collect.size());
        Employee employee = new Employee();
        employee.setAge(123);
        System.out.println(employee);
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
