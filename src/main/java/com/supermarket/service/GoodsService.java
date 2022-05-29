package com.supermarket.service;

import com.supermarket.pojo.Goods;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface GoodsService {
    /**
     * 处理商品添加
     * @param gName
     * @param gPrice
     * @param gCount
     * @param date
     * @return
     */
    public boolean GoodsAdd(String gName, Double gPrice, String gCount, Date date);

    /**
     * 获取商品名称
     * @param gName
     * @return
     */
    public boolean GetName(String gName);

    /**
     * 获取商品列表
     * @return
     */
    public List<Goods> getQueryAllGoods();

    /**
     * 删除商品
     */
    public boolean GoodsDel(Integer id);

    ArrayList<Goods> getGoodsLike(String gName);

    /**
     * 获取编辑商品的信息
     * @return
     */
    public Goods ToGetEdit(Integer id);

    boolean GoodsEdit(Integer id, String gName, Double gPrices, String gCount, Date date);
}
