package com;

import com.supermarket.mapper.GoodsMapper;
import com.supermarket.pojo.Goods;
import com.supermarket.pojo.GoodsExample;
import com.supermarket.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GoodsTest {

    //测试商品添加功能
    @Test
    public void testAddGoods(){
        Date date = new Date(System.currentTimeMillis());
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        GoodsMapper mapper=sqlSession.getMapper(GoodsMapper.class);
        int n= mapper.insert(new Goods(null,"test",12.0,"33",date,date));
        if (n!=0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }

    //测试商品删除功能
    @Test
    public void testDeleteGoods(){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        GoodsMapper mapper=sqlSession.getMapper(GoodsMapper.class);
        int n=mapper.deleteByPrimaryKey(43);
        if (n!=0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }

    //测试商品修改功能
    @Test
    public void testEditGoods(){
        Date date = new Date(System.currentTimeMillis());
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        GoodsMapper mapper=sqlSession.getMapper(GoodsMapper.class);
        int n= mapper.updateByPrimaryKeySelective(new Goods(46,"testedit",123.0,"125",null,date));
        if (n!=0){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }

    //测试商品显示
    @Test
    public void testShowGoods(){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        GoodsMapper mapper=sqlSession.getMapper(GoodsMapper.class);
        List<Goods> goodsList=mapper.selectByExample(null);
        goodsList.forEach(list-> System.out.println(list));
    }

    //测试商品模糊查询
    @Test
    public void testShowLikeGoods(){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        GoodsMapper mapper=sqlSession.getMapper(GoodsMapper.class);
        GoodsExample example=new GoodsExample();
        example.createCriteria().andGNameLike("%"+"薯片"+"%");
        ArrayList<Goods> goodsArrayList= (ArrayList<Goods>) mapper.selectByExample(example);
        goodsArrayList.forEach(list-> System.out.println(list));
    }
}
