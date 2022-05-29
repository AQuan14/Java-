package com.supermarket.JFrame.Admin;

import com.supermarket.controllers.UserController;
import com.supermarket.utils.SpringUtils;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class EditPwdFrame extends JFrame {
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField resetpasswordField;

    /**
     * Create the frame.
     */
    public EditPwdFrame() {
        setTitle("修改密码");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBounds(500, 280, 450, 300);
        contentPane = new JPanel();
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("用户名:");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
        lblNewLabel.setBounds(108, 28, 75, 43);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("新密码:");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(108, 70, 78, 43);
        contentPane.add(lblNewLabel_1);

        usernameField = new JTextField();
        usernameField.setBounds(179, 39, 112, 24);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(179, 81, 112, 23);
        contentPane.add(passwordField);

        JButton resetButton = new JButton("重置");
        resetButton.setBounds(130, 169, 65, 23);
        contentPane.add(resetButton);

        JButton editButton = new JButton("修改");
        editButton.setBounds(226, 169, 65, 23);
        contentPane.add(editButton);

        JLabel lblNewLabel_2 = new JLabel("确认密码:");
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(108, 119, 75, 43);
        contentPane.add(lblNewLabel_2);

        resetpasswordField = new JPasswordField();
        resetpasswordField.setBounds(179, 130, 112, 23);
        contentPane.add(resetpasswordField);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag=false;
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());
                String repassword=new String(resetpasswordField.getPassword());
                UserController userController= SpringUtils.userController();
                flag=userController.editPassword(username,password,repassword);
                if (flag){
                    usernameField.setText("");
                    passwordField.setText("");
                    resetpasswordField.setText("");
                    dispose();
                }
            }
        });
    }
}
