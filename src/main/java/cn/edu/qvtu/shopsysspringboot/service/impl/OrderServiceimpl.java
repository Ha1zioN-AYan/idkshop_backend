package cn.edu.qvtu.shopsysspringboot.service.impl;

import cn.edu.qvtu.shopsysspringboot.mapper.CartMapper;
import cn.edu.qvtu.shopsysspringboot.mapper.OrderMapper;
import cn.edu.qvtu.shopsysspringboot.pojo.Cart;
import cn.edu.qvtu.shopsysspringboot.pojo.Order;
import cn.edu.qvtu.shopsysspringboot.pojo.OrderItem;
import cn.edu.qvtu.shopsysspringboot.service.OrderService;
import cn.edu.qvtu.shopsysspringboot.util.ApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceimpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    
    @Autowired
    CartMapper cartMapper;

    @Override
    public ApiData getOrdersByUsername(String username) {
        if(StringUtils.isEmpty(username)) {
            return ApiData.fail("用户名为空");
        }
        List<Order> orders = orderMapper.getOrdersByUsername(username);
        return ApiData.success(orders);
    }

    @Override
    public ApiData getOrderDetails(String orderNumber) {
        if(StringUtils.isEmpty(orderNumber)) {
            return ApiData.fail("订单号为空");
        }
        
        Order order = orderMapper.getOrderByOrderNumber(orderNumber);
        if(order == null) {
            return ApiData.fail("订单不存在");
        }
        
        List<OrderItem> orderItems = orderMapper.getOrderItemsByOrderNumber(orderNumber);
        
        Map<String, Object> orderDetails = new HashMap<>();
        orderDetails.put("order", order);
        orderDetails.put("items", orderItems);
        
        return ApiData.success(orderDetails);
    }

    @Override
    @Transactional
    public ApiData createOrderFromCart(String username, String address, String phone, List<Cart> cartItems) {
        if(StringUtils.isEmpty(username)) {
            return ApiData.fail("用户名为空");
        }
        if(StringUtils.isEmpty(address)) {
            return ApiData.fail("收货地址为空");
        }
        if(StringUtils.isEmpty(phone)) {
            return ApiData.fail("联系电话为空");
        }
        if(cartItems == null || cartItems.isEmpty()) {
            return ApiData.fail("购物车为空");
        }
        
        String orderNumber = "ORDER" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
        double totalAmount = 0;
        for(Cart item : cartItems) {
            totalAmount += item.getPrice() * item.getQuantity();
        }
        
        Order order = new Order();
        order.setOrderNumber(orderNumber);
        order.setUsername(username);
        order.setTotalAmount(totalAmount);
        order.setStatus("待付款");
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setAddress(address);
        order.setPhone(phone);
        
        int orderResult = orderMapper.createOrder(order);
        if(orderResult <= 0) {
            return ApiData.fail("创建订单失败");
        }
        
        for(Cart item : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderNumber(orderNumber);
            orderItem.setProductId(item.getProductId());
            orderItem.setProductName(item.getProductName());
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setImageurl(item.getImageurl());
            
            int itemResult = orderMapper.addOrderItem(orderItem);
            if(itemResult <= 0) {
                return ApiData.fail("添加订单详情失败");
            }
        }
        
        cartMapper.clearCart(username);
        
        return ApiData.success(orderNumber, "订单创建成功");
    }

    @Override
    public ApiData updateOrderStatus(String orderNumber, String status) {
        if(StringUtils.isEmpty(orderNumber)) {
            return ApiData.fail("订单号为空");
        }
        if(StringUtils.isEmpty(status)) {
            return ApiData.fail("订单状态为空");
        }
        
        int result = orderMapper.updateOrderStatus(orderNumber, status);
        if(result > 0) {
            return ApiData.success("订单状态更新成功");
        } else {
            return ApiData.fail("订单状态更新失败");
        }
    }
}