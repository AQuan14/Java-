package com.supermarket.service.impl;

import com.supermarket.mapper.UserMapper;
import com.supermarket.pojo.User;
import com.supermarket.pojo.UserExample;
import com.supermarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public boolean userLogin(String username, String password){
        //boolean login=userDao.userLogin(username,password);
        boolean flag=true;
        UserExample example=new UserExample();
        example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> user= userMapper.selectByExample(example);
        if (user.size()==0){
            flag=false;
        }
        return flag;

    }

    @Override
    public boolean editPassword(String username, String password, String repassword) {
        boolean flag=false;
        int n=userMapper.updateByPrimaryKey(new User(1,username,password));
        if (n!=0){
            flag=true;
        }
        return flag;
    }

    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }


}
