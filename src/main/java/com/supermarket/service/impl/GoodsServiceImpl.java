package com.supermarket.service.impl;

import com.supermarket.mapper.GoodsMapper;
import com.supermarket.pojo.Goods;

import com.supermarket.pojo.GoodsExample;
import com.supermarket.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public boolean GoodsAdd(String gName, Double gPrice, String gCount, Date date) {
        boolean flag=false;
        int n= goodsMapper.insert(new Goods(null,gName,gPrice,gCount,date,date));
        if (n!=0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean GetName(String gName) {
        boolean flag=false;
        GoodsExample example=new GoodsExample();
        example.createCriteria().andGNameEqualTo(gName);
        List<Goods> list=goodsMapper.selectByExample(example);
        if (list.size()!=0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Goods> getQueryAllGoods() {
        List<Goods> lists=goodsMapper.selectByExample(null);
        return lists;
    }

    @Override
    public boolean GoodsDel(Integer id) {
        boolean flag=false;
        int n=goodsMapper.deleteByPrimaryKey(id);
        if (n!=0){
            flag=true;
        }
        return flag;
    }

    @Override
    public ArrayList<Goods> getGoodsLike(String gName) {
        ArrayList<Goods> list=new ArrayList<Goods>();
        if (gName.length()==0){
            list= (ArrayList<Goods>) goodsMapper.selectByExample(null);
        }else {
            GoodsExample example=new GoodsExample();
            example.createCriteria().andGNameLike("%"+gName+"%");
            list= (ArrayList<Goods>) goodsMapper.selectByExample(example);
        }
//        System.out.println(list);
        return list;
    }

    @Override
    public Goods ToGetEdit(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean GoodsEdit(Integer id, String gName, Double gPrices, String gCount, Date date) {
        boolean flag=false;
        int n= goodsMapper.updateByPrimaryKeySelective(new Goods(id,gName,gPrices,gCount,null,date));
        if (n!=0){
            flag=true;
        }
        return flag;
    }


}
