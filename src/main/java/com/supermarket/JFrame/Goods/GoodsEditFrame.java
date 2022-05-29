package com.supermarket.JFrame.Goods;

import com.supermarket.controllers.GoodsController;
import com.supermarket.pojo.Goods;
import com.supermarket.utils.WindowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Component
public class GoodsEditFrame extends JFrame{
    private JPanel contentPane;
    private JTextField goodsName;
    private JTextField goodsPrice;
    private JTextField goodsCount;

    @Autowired
    private GoodsController goodsController;

    GoodsListFrame goodsListFrame =new GoodsListFrame();
    /**
     * Create the frame.
     */
    public void GoodsEditFrame(Integer id) {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBounds(0, 0, 530, 380);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("编辑商品界面");
        lblNewLabel.setFont(new Font("新宋体", Font.PLAIN, 35));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(123, 27, 275, 45);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("商品名称:");
        lblNewLabel_1.setFont(new Font("新宋体", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(153, 90, 99, 27);
        contentPane.add(lblNewLabel_1);

        goodsName = new JTextField();
        goodsName.setBounds(249, 92, 109, 25);
        contentPane.add(goodsName);
        goodsName.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("商品价格:");
        lblNewLabel_3.setFont(new Font("新宋体", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(153, 145, 99, 27);
        contentPane.add(lblNewLabel_3);

        goodsPrice = new JTextField();
        goodsPrice.setBounds(249, 147, 109, 25);
        contentPane.add(goodsPrice);
        goodsPrice.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("商品数量:");
        lblNewLabel_4.setFont(new Font("新宋体", Font.PLAIN, 18));
        lblNewLabel_4.setBounds(153, 204, 99, 27);
        contentPane.add(lblNewLabel_4);

        goodsCount = new JTextField();
        goodsCount.setBounds(249, 206, 109, 25);
        contentPane.add(goodsCount);
        goodsCount.setColumns(10);

        JButton goodsEditButton = new JButton("提交");
        goodsEditButton.setFont(new Font("新宋体", Font.PLAIN, 18));
        goodsEditButton.setBounds(219, 260, 80, 27);
        contentPane.add(goodsEditButton);

        this.setResizable(false);//限制窗口不可最大化
        WindowUtil.setFrameCenter(this);//设置窗体居中。

        final Integer gid=goodsController.getId(id);

        Goods goods=goodsController.ToGetEditMessage(id);
        goodsName.setText(goods.getgName());
        String gPrice=String.valueOf(goods.getgPrice());
        goodsPrice.setText(gPrice);
        goodsCount.setText(goods.getgCount());
        /**
         * 修改商品
         */
        goodsEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gName=goodsName.getText().trim();
                String gPrice=goodsPrice.getText();
                String gCount=goodsCount.getText().trim();
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                boolean flag=goodsController.GoodsEdit(gid,gName,gPrice,gCount,date);
                if (flag){
                    goodsName.setText("");
                    goodsPrice.setText("");
                    goodsCount.setText("");
                    dispose();
                }
            }
        });
    }


}

