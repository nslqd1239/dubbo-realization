package com.nsl;

import com.nsl.consumer.DubboConsumer;
import com.nsl.service.UserService;

/**
 * Hello world!
 */
public class Start {

    public static void main(String[] args) {

        DubboConsumer<UserService> consumer = new DubboConsumer<>();
        consumer.setInterface(UserService.class);

        UserService userService = consumer.get();
        String result = userService.getName("name");
        System.out.println(result);
    }
}
