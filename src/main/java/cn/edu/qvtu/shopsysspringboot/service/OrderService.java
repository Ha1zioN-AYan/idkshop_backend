package cn.edu.qvtu.shopsysspringboot.service;

import cn.edu.qvtu.shopsysspringboot.pojo.Cart;
import cn.edu.qvtu.shopsysspringboot.util.ApiData;

import java.util.List;

public interface OrderService {
    ApiData getOrdersByUsername(String username);
    
    ApiData getOrderDetails(String orderNumber);
    
    ApiData createOrderFromCart(String username, String address, String phone, List<Cart> cartItems);
    
    ApiData updateOrderStatus(String orderNumber, String status);
}