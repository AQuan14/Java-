package com.supermarket.controllers;

import com.supermarket.JFrame.Admin.EditPwdFrame;
import com.supermarket.JFrame.Index.IndexFrame;
import com.supermarket.JFrame.Login.LoginFrame;
import com.supermarket.pojo.User;
import com.supermarket.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.swing.*;

@Component
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    private User user;
    @Autowired
    private IndexFrame indexFrame;
    @Autowired
    private EditPwdFrame editPwdFrame;
    private LoginFrame loginFrame=new LoginFrame();

    public boolean login(String username,String password) throws Exception {
        String pwd= DigestUtils.md5DigestAsHex(password.getBytes());
        boolean flag= userServiceImpl.userLogin(username,pwd);
        System.out.println(flag);
        if(username.equals("")){
            JOptionPane.showMessageDialog(loginFrame, "用户名不能为空","",JOptionPane.WARNING_MESSAGE);
            return false;
        }else if (password.equals("")){
            JOptionPane.showMessageDialog(loginFrame, "密码不能为空","",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(flag){
            indexFrame.setVisible(true);
            return true;
        }else{
            JOptionPane.showMessageDialog(loginFrame, "用户名或密码错误","",JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    public void ToEditPwd(){
        editPwdFrame.setVisible(true);
    }

    public boolean editPassword(String username,String password,String repassword){
        User user= userServiceImpl.getUser();
        String pwd= DigestUtils.md5DigestAsHex(password.getBytes());
        boolean flag=false;
        if(username.equals("")){
            JOptionPane.showMessageDialog(editPwdFrame, "用户名不能为空","警告",JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(password.equals("")||repassword.equals("")){
            JOptionPane.showMessageDialog(editPwdFrame, "密码不能为空","警告",JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(!password.equals(repassword)){
            JOptionPane.showMessageDialog(editPwdFrame, "两次密码不相同","警告",JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(user.getPassword().equals(pwd)){
            JOptionPane.showMessageDialog(editPwdFrame, "与原密码相同","警告",JOptionPane.WARNING_MESSAGE);
            return false;
        }else{
            flag= userServiceImpl.editPassword(username,pwd,repassword);
            JOptionPane.showMessageDialog(editPwdFrame, "修改成功","信息",JOptionPane.WARNING_MESSAGE);
        }
        return flag;
    }
}
