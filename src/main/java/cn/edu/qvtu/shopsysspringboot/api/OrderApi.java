package cn.edu.qvtu.shopsysspringboot.api;

import cn.edu.qvtu.shopsysspringboot.pojo.Cart;
import cn.edu.qvtu.shopsysspringboot.service.OrderService;
import cn.edu.qvtu.shopsysspringboot.util.ApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderApi {

    @Autowired
    OrderService orderService;

    // 根据用户名查询所有订单
    @GetMapping("/orders/{username}")
    public ApiData getOrdersByUsername(@PathVariable String username) {
        return orderService.getOrdersByUsername(username);
    }

    // 根据订单号查询订单详情
    @GetMapping("/order/details/{orderNumber}")
    public ApiData getOrderDetails(@PathVariable String orderNumber) {
        return orderService.getOrderDetails(orderNumber);
    }

    // 创建订单（从购物车结算）
    @PostMapping("/order/create")
    public ApiData createOrder(@RequestParam String username,
                              @RequestParam String address,
                              @RequestParam String phone,
                              @RequestBody List<Cart> cartItems) {
        return orderService.createOrderFromCart(username, address, phone, cartItems);
    }

    // 更新订单状态
    @PutMapping("/order/status")
    public ApiData updateOrderStatus(@RequestParam String orderNumber,
                                   @RequestParam String status) {
        return orderService.updateOrderStatus(orderNumber, status);
    }
}