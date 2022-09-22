package com.hznu.lambda;

import org.junit.Test;
import com.hznu.lambda.entity.Employee;
import com.hznu.lambda.entity.EmployeeData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author LIN
 * @date 2022/8/12 10:40
 */
public class MapTest {

    @Test
    public void testNum() {

        //迭代
        //public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        //遍历前10个偶数
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        //生成
        //public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    @Test
    public void testAPI() {
        List<Employee> employees = EmployeeData.getEmployees();
        //filter(Predicate p)——接收 Lambda ， 从流中排除某些元素。
        Stream<Employee> employeeStream = employees.stream();
        //练习：查询员工表中薪资大于7000的员工信息
        employees.stream().filter(e -> e.getSalary() < 7000).forEach(System.out::println);

        //limit(n)——截断流，使其元素不超过给定数量。
        employees.stream().limit(3).forEach(System.out::println);
        System.out.println();

        //skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
        employees.stream().skip(3).forEach(System.out::println);
        //distinct()——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
        employees.add(new Employee(1010, "刘庆东", 56.0, 8000));
        employees.add(new Employee(1010, "刘庆东", 56.0, 8000));
        employees.add(new Employee(1010, "刘庆东", 56.0, 8000));
        employees.add(new Employee(1010, "刘庆东", 56.0, 8000));

        employees.stream().distinct().forEach(System.out::println);
    }

    //2-映射
    @Test
    public void testMap() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        //map(Function f)——接收一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        //练习1：获取员工姓名长度大于3的员工的姓名。
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> nameStream = employees.stream().map(Employee::getName);
        nameStream.filter(name -> name.length() > 3).forEach(System.out::println);
        System.out.println();
        //练习2：使用map()中间操作实现flatMap()中间操作方法
        Stream<Stream<Character>> streamStream = list.stream().map(MapTest::fromStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });
        System.out.println();
        //flatMap(Function f)——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        Stream<Character> characterStream = list.stream().flatMap(MapTest::fromStringToStream);
        characterStream.forEach(System.out::println);

    }

    //将字符串中的多个字符构成的集合转换为对应的Stream的实例
    public static Stream<Character> fromStringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c :
                str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    //map()和flatMap()方法类似于List中的add()和addAll()方法
    @Test
    public void test() {
        ArrayList<Object> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        ArrayList<Object> list2 = new ArrayList<>();
        list2.add(5);
        list2.add(6);
        list2.add(7);
        list2.add(8);

        list1.add(list2);
        System.out.println(list1);//[1, 2, 3, 4, [5, 6, 7, 8]]
        list1.addAll(list2);
        System.out.println(list1);//[1, 2, 3, 4, [5, 6, 7, 8], 5, 6, 7, 8]

    }

    //3-排序
    @Test
    public void testSort(){
        //sorted()——自然排序
        List<Integer> list = Arrays.asList(12, 34, 54, 65, 32);
        list.stream().sorted().forEach(System.out::println);

        //抛异常，原因:Employee没有实现Comparable接口
        List<Employee> employees = EmployeeData.getEmployees();
//        employees.com.hznu.lambda.com.hznu.entity.stream().sorted().forEach(System.out::println);

        //sorted(Comparator com)——定制排序
        List<Employee> employees1 = EmployeeData.getEmployees();
        employees1.stream().sorted((e1,e2)->{
            int ageValue = Integer.compare(e1.getAge(), e2.getAge());
            if (ageValue != 0){
                return ageValue;
            }else {
                return -Double.compare(e1.getSalary(),e2.getSalary());
            }

        }).forEach(System.out::println);
    }




}
