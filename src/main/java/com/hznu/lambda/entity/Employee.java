package com.hznu.lambda.entity;

import lombok.Data;

/**
 * @author LIN
 * @date 2022/8/12 10:53
 */

@Data
public class Employee {

    private Integer id;
    private String name;
    private Double salary;
    private Integer age;

    public Employee(Integer id, String name, Double salary, Integer age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
}
