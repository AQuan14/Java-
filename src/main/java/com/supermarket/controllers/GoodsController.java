package com.supermarket.controllers;

import com.supermarket.JFrame.Goods.GoodsAddFrame;
import com.supermarket.JFrame.Goods.GoodsEditFrame;
import com.supermarket.JFrame.Goods.GoodsListFrame;
import com.supermarket.pojo.Goods;
import com.supermarket.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsAddFrame goodsAddFrame;

    @Autowired
    private GoodsListFrame goodsListFrame;

    @Autowired
    private GoodsEditFrame goodsEditFrame;

    /**
     * 跳转到商品添加商品
     */
    public void ToAddGoods(){
       goodsAddFrame.setVisible(true);
    }

    /**
     * 跳转到商品列表
     */
    public void ToGetGoodsList() {
        goodsListFrame.setVisible(true);
    }
    /**
     * 跳转到编辑页面
     */
    public void ToEditFrame(Integer id){
        goodsEditFrame.GoodsEditFrame(id);
        goodsEditFrame.setVisible(true);
    }
    /**
     * 获取全部商品
     */
    public List<Goods> getAllGoods(){
        List<Goods> lists= goodsService.getQueryAllGoods();
        return lists;
    }

    /**
     * 处理商品查询
     */
    public ArrayList<Goods> getGoodsLike(String gName){
        ArrayList<Goods> list=new ArrayList<Goods>();
            list=goodsService.getGoodsLike(gName);
        return list;
    }

    /**
     * 处理商品添加
     */
    public boolean GoodsAdd(String gName, String gPrice, String gCount, Date date){
        boolean flag=false;
        flag=goodsJudgment(gName,gPrice,gCount);
        if (flag){
            Double gPrices=Double.parseDouble(gPrice);
            flag= goodsService.GoodsAdd(gName,gPrices,gCount,date);
            JOptionPane.showMessageDialog(goodsAddFrame, "添加成功", "信息", JOptionPane.WARNING_MESSAGE);
        }
        return flag;
    }



    /**
     * 删除商品
     */
    public void GoodsDel(Integer id){
        boolean flag=goodsService.GoodsDel(id);
        if (flag){
            JOptionPane.showMessageDialog(goodsListFrame, "删除成功", "信息", JOptionPane.WARNING_MESSAGE);
        }else
            JOptionPane.showMessageDialog(goodsListFrame, "删除失败", "警告", JOptionPane.WARNING_MESSAGE);
    }


    /**
     * 提示没有选中编辑按钮
     */
    public void Warning(){
        JOptionPane.showMessageDialog(goodsListFrame, "请选择商品", "警告", JOptionPane.WARNING_MESSAGE);
    }


    /**
     * 获取编辑id
     */
    public Integer getId(Integer id){
        return id;
    }

    /**
     * 得到编辑ID的信息
     * @param id
     * @return
     */
    public Goods ToGetEditMessage(Integer id) {
       return goodsService.ToGetEdit(id);
    }

    /**
     * 修改商品信息
     */
    public boolean GoodsEdit(Integer id , String gName, String gPrice, String gCount, Date date) {
        boolean flag=false;
        System.out.println(gPrice);
        flag=goodsJudgment(gName,gPrice,gCount);
        if (flag){
            Double gPrices=Double.parseDouble(gPrice);
            flag= goodsService.GoodsEdit(id,gName,gPrices,gCount,date);
            JOptionPane.showMessageDialog(goodsAddFrame, "修改成功", "信息", JOptionPane.WARNING_MESSAGE);
        }
        return flag;
    }

    /**
     * 商品判断
     * @return
     */
    public boolean goodsJudgment(String gName, String gPrice, String gCount){

//        boolean flag=GetName(gName);

        if (gName.equals("")){
            JOptionPane.showMessageDialog(goodsAddFrame, "请输入商品名称", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(gPrice.equals("")){
            JOptionPane.showMessageDialog(goodsAddFrame, "请输入商品价格", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (gCount.equals("")){
            JOptionPane.showMessageDialog(goodsAddFrame, "请输入商品数量", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if (gName.length()>10){
            JOptionPane.showMessageDialog(goodsAddFrame, "商品名字超出限制", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if (gCount.length()>8){
            JOptionPane.showMessageDialog(goodsAddFrame, "商品添加数量超出限制", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if (gPrice.length()>8){
            JOptionPane.showMessageDialog(goodsAddFrame, "商品价格超出规范", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(!gPrice.equals("")) {
            for (int i = 0; i<gPrice.length();i++ ) {
                char p[]=new char[gPrice.length()];
                p[i]=gPrice.charAt(i);
                if (p[0]=='0'){
                    JOptionPane.showMessageDialog(goodsAddFrame, "商品价格不规范", "警告", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }

        }
        if(gPrice.equals(gPrice)) {
            try{
                Double gP=Double.parseDouble(gPrice);
            }catch (Exception e){
                JOptionPane.showMessageDialog(goodsAddFrame, "请用数字输入商品价格", "警告", JOptionPane.WARNING_MESSAGE);
            }
        }
        if(gCount.equals(gCount)) {
            for (int i = gCount.length(); --i >= 0; ) {
                if (!Character.isDigit(gCount.charAt(i))) {
                    JOptionPane.showMessageDialog(goodsAddFrame, "请用数字输入商品数量", "警告", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }

//        if (flag){
//            JOptionPane.showMessageDialog(goodsAddFrame, "仓库已有同名商品", "警告", JOptionPane.WARNING_MESSAGE);
//            return false;
//        }
        return true;
    }


}
