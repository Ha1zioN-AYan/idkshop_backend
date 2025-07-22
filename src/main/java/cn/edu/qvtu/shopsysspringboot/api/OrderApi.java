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

    @GetMapping("/orders/{username}")
    public ApiData getOrdersByUsername(@PathVariable String username) {
        return orderService.getOrdersByUsername(username);
    }

    @GetMapping("/order/details/{orderNumber}")
    public ApiData getOrderDetails(@PathVariable String orderNumber) {
        return orderService.getOrderDetails(orderNumber);
    }

    @PostMapping("/order/create")
    public ApiData createOrder(@RequestParam String username,
                              @RequestParam String address,
                              @RequestParam String phone,
                              @RequestBody List<Cart> cartItems) {
        return orderService.createOrderFromCart(username, address, phone, cartItems);
    }

    @PutMapping("/order/status")
    public ApiData updateOrderStatus(@RequestParam String orderNumber,
                                   @RequestParam String status) {
        return orderService.updateOrderStatus(orderNumber, status);
    }
}