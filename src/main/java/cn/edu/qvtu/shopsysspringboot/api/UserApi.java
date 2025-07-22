package cn.edu.qvtu.shopsysspringboot.api;


import cn.edu.qvtu.shopsysspringboot.pojo.Users;
import cn.edu.qvtu.shopsysspringboot.service.UserService;
import cn.edu.qvtu.shopsysspringboot.util.ApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserApi {

    @Autowired
    UserService userService;

    //登录功能
    @PostMapping("/login")
    public ApiData login(String phonenumber, String userpwd) {
        return userService.login(phonenumber, userpwd);
    }

    //注册功能
    @PostMapping("/register")
    public ApiData register(@RequestBody Users users) {
        return userService.register(users);
    }

    //检查管理员状态
    @GetMapping("/check-admin/{phonenumber}")
    public ApiData checkAdminStatus(@PathVariable String phonenumber) {
        return userService.checkAdminStatus(phonenumber);
    }


}
