package com.supermarket.JFrame.Index;

import com.supermarket.JFrame.Login.LoginFrame;
import com.supermarket.controllers.GoodsController;
import com.supermarket.controllers.UserController;
import com.supermarket.utils.WindowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class IndexFrame extends JFrame{
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    @Autowired
    private UserController userController;
    @Autowired
    private GoodsController goodsController;

    /**
     * Create the frame.
     */
    public IndexFrame(){
        setFont(new Font("微软雅黑", Font.PLAIN, 14));
        setTitle("超市管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(360,220, 750, 500);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu goods_manage = new JMenu("商品管理");
        goods_manage.setForeground(Color.DARK_GRAY);
        goods_manage.setBackground(Color.BLACK);
        goods_manage.setHorizontalAlignment(SwingConstants.CENTER);
        goods_manage.setFont(new Font("新宋体", Font.BOLD, 15));
        menuBar.add(goods_manage);


        JMenuItem get_goods = new JMenuItem("商品列表");
        get_goods.setHorizontalAlignment(SwingConstants.CENTER);
        get_goods.setForeground(Color.BLACK);
        goods_manage.add(get_goods);

        JMenuItem add_goods = new JMenuItem("添加商品");
        add_goods.setHorizontalAlignment(SwingConstants.CENTER);
        add_goods.setForeground(Color.BLACK);
        goods_manage.add(add_goods);

        JMenu system_set = new JMenu("设置");
        system_set.setHorizontalAlignment(SwingConstants.CENTER);
        system_set.setForeground(Color.DARK_GRAY);
        system_set.setFont(new Font("新宋体", Font.BOLD, 15));
        menuBar.add(system_set);

        JMenuItem file_feedback = new JMenuItem("文件反馈");
        file_feedback.setHorizontalAlignment(SwingConstants.CENTER);
        system_set.add(file_feedback);

        JMenuItem edit_pwd = new JMenuItem("修改密码");
        system_set.add(edit_pwd);

        JMenuItem system_exit = new JMenuItem("退出");
        system_set.add(system_exit);

        this.setSize(750, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        WindowUtil.setFrameCenter(this);//设置窗体居中。
        IndexBgImage indexBgImage = new IndexBgImage();
        setContentPane(indexBgImage);


        /**
         * 商品列表
         */
        get_goods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodsController.ToGetGoodsList();
            }
        });

        /**
         * 商品添加
         */
        add_goods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GoodsController goodsController=SpringUtils.goodsController();
                goodsController.ToAddGoods();
            }
        });

        /**
         * 修改密码监听器
         */
        edit_pwd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //UserController userController= SpringUtils.userController();
                userController.ToEditPwd();
            }
        });

        /**
         * 退出系统监听器
         */
        system_exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginFrame frame=new LoginFrame();
                frame.setVisible(true);
//                System.exit(1);
            }
        });

    }
}
