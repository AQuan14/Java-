package com.supermarket.JFrame.Goods;


import com.supermarket.controllers.GoodsController;
import com.supermarket.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.util.List;
import java.util.Vector;

@Component
public class GoodsListFrame extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField good_name;
    private JTextField goodPrice;
    private JTextField goodCount;

    private DefaultTableModel dtm=null;

    private Boolean flag=false;
    @Autowired
    private GoodsController goodsController;

    /**
     * Create the frame.
     */
    public GoodsListFrame() {
        setTitle("商品列表");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBounds(420, 251, 600, 453);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 10, 585, 300);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "商品编号", "商品名称", "商品价格", "商品库存", "上架时间", "更新时间"
                }
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(91);
        table.getColumnModel().getColumn(4).setPreferredWidth(125);
        table.getColumnModel().getColumn(5).setPreferredWidth(125);
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("商品名称");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("新宋体", Font.PLAIN, 15));
        lblNewLabel.setBounds(47, 340, 73, 21);
        contentPane.add(lblNewLabel);

        good_name = new JTextField();
        good_name.setBounds(118, 340, 75, 25);
        contentPane.add(good_name);
        good_name.setColumns(10);


        JButton getGoodButton = new JButton("查询");
        getGoodButton.setFont(new Font("新宋体", Font.PLAIN, 12));
        getGoodButton.setBounds(291, 329, 93, 23);
        contentPane.add(getGoodButton);

        JButton delGoodButton = new JButton("删除");
        delGoodButton.setFont(new Font("新宋体", Font.PLAIN, 12));
        delGoodButton.setBounds(419, 329, 93, 23);
        contentPane.add(delGoodButton);

        JButton editGoodButton = new JButton("修改");
        editGoodButton.setFont(new Font("新宋体", Font.PLAIN, 12));
        editGoodButton.setBounds(291, 367, 93, 23);
        contentPane.add(editGoodButton);

        final JButton showButton = new JButton("刷新");
        showButton.setFont(new Font("新宋体", Font.PLAIN, 12));
        showButton.setBounds(419, 367, 93, 23);
        contentPane.add(showButton);

        this.dtm= (DefaultTableModel) table.getModel();

        //格式化时间
//        final DateFormat mediumDateFormat = DateFormat.getDateTimeInstance
//                (DateFormat.MEDIUM, DateFormat.MEDIUM);

        //设置表数据居中显示

        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cr);


        //设置表头居中显示
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        table.getTableHeader().setDefaultRenderer(hr);

        this.getRootPane().setDefaultButton(showButton);//获取焦点
        showButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyText(e.getKeyCode()).compareToIgnoreCase(
                        "Enter") == 0) {
                    showButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        /**
         * 查询商品
         */
        getGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gName=good_name.getText();

                //格式化时间
                final DateFormat mediumDateFormat = DateFormat.getDateTimeInstance
                        (DateFormat.MEDIUM, DateFormat.MEDIUM);
                dtm.setRowCount(0);
                List<Goods> list= goodsController.getGoodsLike(gName);
                for(Goods goods:list){
                    Vector v=new Vector();
                    v.add(goods.getgId());
                    v.add(goods.getgName());
                    v.add(goods.getgPrice());
                    v.add(goods.getgCount());
                    v.add(mediumDateFormat.format(goods.getgCreattime()));
                    v.add(mediumDateFormat.format(goods.getgUpdatetime()));
                    dtm.addRow(v);
                }
                reTrue();
            }
        });

        /**
         * 编辑商品跳转
         */
        editGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n=table.getSelectedRow();
                if (!flag){
                    goodsController.Warning();
                }
                else if(n==-1){
                    goodsController.Warning();
                }
                else {
                     String id=dtm.getValueAt(table.getSelectedRow(),0).toString();
                     Integer gid=Integer.parseInt(id);
                    goodsController.ToEditFrame(gid);
                }
            }
        });

        /**
         * 删除商品
         */
        delGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n=table.getSelectedRow();
                if (!flag){
                    goodsController.Warning();
                }else if(n==-1){
                    goodsController.Warning();
                }else {
                    String id=dtm.getValueAt(table.getSelectedRow(),0).toString();
                    Integer gid=Integer.parseInt(id);
                    goodsController.GoodsDel(gid);
                    showGoods();
                }

            }
        });

        /**
         * 刷新商品列表
         */
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGoods();
            }
        });
        //WindowUtil.setFrameCenter(this);//设置窗体居中。


    }

    /**
     * 封装商品列表信息
     */
    public boolean showGoods(){
        //格式化时间
        final DateFormat mediumDateFormat = DateFormat.getDateTimeInstance
                (DateFormat.MEDIUM, DateFormat.MEDIUM);
        dtm.setRowCount(0);
        List<Goods> list= goodsController.getAllGoods();
        for(Goods goods:list){
            Vector v=new Vector();
            v.add(goods.getgId());
            v.add(goods.getgName());
            v.add(goods.getgPrice());
            v.add(goods.getgCount());
            v.add(mediumDateFormat.format(goods.getgCreattime()));
            v.add(mediumDateFormat.format(goods.getgUpdatetime()));
            dtm.addRow(v);
        }
        flag=true;
        return flag;
    }

    /**
     * 返回真值
     */
    public boolean reTrue(){
        flag=true;
        return flag;
    }
}
