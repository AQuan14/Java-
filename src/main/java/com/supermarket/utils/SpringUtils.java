package com.supermarket.utils;

import com.supermarket.controllers.GoodsController;
import com.supermarket.controllers.UserController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {
    public static UserController userController(){
        UserController userController=null;
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring.xml");
        userController = (UserController) applicationContext.getBean("userController");
        return userController;
    }

    public static GoodsController goodsController(){
        GoodsController goodsController=null;
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring.xml");
        goodsController=(GoodsController)applicationContext.getBean("goodsController");
        return goodsController;
    }

}
