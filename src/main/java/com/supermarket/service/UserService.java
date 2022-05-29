package com.supermarket.service;

import com.supermarket.pojo.User;

public interface UserService {
    public boolean userLogin(String username, String pwd);

    public boolean editPassword(String username, String password, String repassword);

    public User getUser();

}
