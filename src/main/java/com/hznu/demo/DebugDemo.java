package com.hznu.demo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LIN
 * @date 2022/12/28 10:02
 */
@SpringBootApplication
public class DebugDemo implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplication(DebugDemo.class).run(args);
    }

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setId(1l);
        user1.setName("11");
        User user2 = new User();
        user2.setId(3l);
        user2.setName("33");
        user1.setUser(user2);
        System.out.println(user1);
        userService.say(user1);
        System.out.println(user1);
        // String stream = userService.stream();
        //
        // Map<String, String> map1 = new HashMap<>();
        // map1.put("1","1");
        // System.out.println(map1);
        // Map<String, String> map2 = new HashMap<>(map1);
        // map2.put("2","2");
        // System.out.println(map2);
        //
        // System.out.println(TransType.NORMAL);
        // String stream2 = userService.stream();
        //
        // person.setId(2L);
        // person.setName("小红");
        //
        // boolean flag = userService.getFlag();
        // String stream1 = userService.stream();

    }

}

@Component
class UserService{

    /**
     * 输出用户user的name
     * @param user 用户实体
     */
    public void say(User user){
        User user1 = user.getUser();
        user1.setId(123l);
    }

    public boolean getFlag(){
        return false;
    }

    public String stream(){
        String strTestji = "111";
        List<String> stringList = new ArrayList<>();
        List<String> collect1 = stringList.stream().filter(e -> e.length() == 2).collect(Collectors.toList());
        System.out.println(collect1);
        stringList.add(strTestji);
        stringList.add("2");
        stringList.add("2");
        String collect = stringList.stream().map(Integer::valueOf)
                .distinct().map(each -> each + 1)
                .map(Object::toString)
                .collect(Collectors.joining(","));
        return collect;
    }

    public void bye(){
        System.out.println("bye");
    }

}

@Data
class User{

    private Long id;

    private String name;

    private User user;
}
