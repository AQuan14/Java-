package com.supermarket.JFrame.Login;

import com.supermarket.controllers.UserController;
import com.supermarket.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginFrame extends JFrame {

    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;

    @Autowired
    @Qualifier(value = "userController")
    private UserController userController;


    /**
     * Create the frame.
     */
    public LoginFrame(){

        setTitle("管理员登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 280, 450, 300);

        contentPane = new JPanel();
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("用户名：");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
        lblNewLabel.setBounds(111, 60, 175, 43);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("密  码：");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(111, 114, 75, 43);
        contentPane.add(lblNewLabel_1);

        usernameField = new JTextField();
        usernameField.setBounds(179, 72, 112, 24);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 125, 111, 24);
        contentPane.add(passwordField);

        JButton resetButton = new JButton("重置");
        resetButton.setBounds(132, 169, 65, 23);
        contentPane.add(resetButton);

        JButton loginButton = new JButton("登录");
        loginButton.setBounds(226, 169, 65, 23);

        contentPane.add(loginButton);

        /**
         *登录监听器
         */
        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UserController userController= SpringUtils.userController();
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());
                try {
                    boolean flag= userController.login(username,password);
                    if(flag){
                        dispose();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        /**
         * 重置监听器
         */
        resetButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");

            }
        });

        /**
         * 固定窗口大小，不可以放大
         */
        this.setSize(450, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }
}
