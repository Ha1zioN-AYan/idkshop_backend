package cn.edu.qvtu.shopsysspringboot.service;


import cn.edu.qvtu.shopsysspringboot.pojo.Users;
import cn.edu.qvtu.shopsysspringboot.util.ApiData;

public interface UserService {
    ApiData login(String phonenumber, String userpwd);

    ApiData register(Users users);
    
    ApiData checkAdminStatus(String phonenumber);
}
