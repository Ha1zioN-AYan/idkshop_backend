package cn.edu.qvtu.shopsysspringboot.service.impl;


import cn.edu.qvtu.shopsysspringboot.mapper.UserMapper;
import cn.edu.qvtu.shopsysspringboot.pojo.Users;
import cn.edu.qvtu.shopsysspringboot.service.UserService;
import cn.edu.qvtu.shopsysspringboot.util.ApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ApiData login(String phonenumber, String userpwd) {
        //判断账号密码是否为空
        if (StringUtils.isEmpty(phonenumber)) {
            return ApiData.fail("手机号为空");
        }
        if (StringUtils.isEmpty(userpwd)) {
            return ApiData.fail("密码为空");
        }
        //判断账号密码是否正确
        Users users = userMapper.login(phonenumber);
        if (users == null) {
            return ApiData.fail("手机不存在");
        }
        if (!users.getUserpwd().equals(userpwd)) {
            return ApiData.fail("密码错误");
        }
        //登录成功，返回用户信息和跳转路径
        String redirectPath = users.getIsadmin() == 1 ? "/admin" : "/user";
        return ApiData.success(new Object[]{users, redirectPath});
    }

    @Override
    public ApiData register(Users users) {
        if (StringUtils.isEmpty(users.getPhonenumber())){
            return ApiData.fail("账号为空");
        }
        if (StringUtils.isEmpty(users.getUserpwd())){
            return ApiData.fail("密码为空");
        }
        if (StringUtils.isEmpty(users.getUsername())){
            return ApiData.fail("用户名为空");
        }
        int i = userMapper.register(users);
        if (i > 0){
            return ApiData.success("注册成功");
        }
        else {
            return ApiData.fail("注册失败");
        }
    }

    @Override
    public ApiData checkAdminStatus(String phonenumber) {
        if (StringUtils.isEmpty(phonenumber)) {
            return ApiData.fail("手机号为空");
        }
        
        Users user = userMapper.getUserByPhone(phonenumber);
        if (user == null) {
            return ApiData.fail("用户不存在");
        }
        
        boolean isAdmin = user.getIsadmin() == 1;
        String redirectPath = isAdmin ? "/admin" : "/user";
        
        return ApiData.success(new Object[]{isAdmin, redirectPath});
    }

}
