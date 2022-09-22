package com.hznu.lambda.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LIN
 * @date 2022/8/12 10:53
 */
public class EmployeeData {

    public static List<Employee> getEmployees(){
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1010,"s庆东",5600.0,8));
        employees.add(new Employee(1011,"q庆东",5500.0,9));
        return employees;
    }
}
