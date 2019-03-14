package com.nsl;

import com.nsl.provider.DubboProvider;
import com.nsl.service.UserService;
import com.nsl.service.impl.UserServiceImpl;

public class Start {

    public static void main(String[] args) throws InterruptedException {
        //要发布的服务
        UserService helloWorldService = new UserServiceImpl();

        //发布服务
        DubboProvider<UserService> dubboProvider = new DubboProvider<>();
        dubboProvider.setPort(3347);
        dubboProvider.setRef(helloWorldService);
        dubboProvider.setInterface(UserService.class);

        dubboProvider.export();
    }
}
