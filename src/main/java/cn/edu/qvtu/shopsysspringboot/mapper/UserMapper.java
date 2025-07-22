package cn.edu.qvtu.shopsysspringboot.mapper;

import cn.edu.qvtu.shopsysspringboot.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    int register(Users users);

    Users login(@Param("phonenumber") String phonenumber);
    
    Users getUserByPhone(@Param("phonenumber") String phonenumber);
}
