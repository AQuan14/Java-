package com;

import com.supermarket.mapper.UserMapper;
import com.supermarket.pojo.User;
import com.supermarket.pojo.UserExample;
import com.supermarket.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.util.List;

public class UserTest {

    //测试用户登录功能
    @Test
    public void testUserLogin(){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        UserExample example=new UserExample();
        example.createCriteria().andUsernameEqualTo("admin").andPasswordEqualTo("202cb962ac59075b964b07152d234b70");
        List<User> user= mapper.selectByExample(example);
        if (user!=null){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }

    //测试用户修改密码功能
    @Test
    public void testEditPwd(){
        String username="test";
        String password="123";
        String pwd=DigestUtils.md5DigestAsHex(password.getBytes());
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        int n=mapper.updateByPrimaryKey(new User(2,username,pwd));
        if (n!=0){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }
}
