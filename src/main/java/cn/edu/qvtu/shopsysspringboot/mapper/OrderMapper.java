package cn.edu.qvtu.shopsysspringboot.mapper;

import cn.edu.qvtu.shopsysspringboot.pojo.Order;
import cn.edu.qvtu.shopsysspringboot.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> getOrdersByUsername(String username);
    
    Order getOrderByOrderNumber(String orderNumber);
    
    int createOrder(Order order);
    
    int updateOrderStatus(String orderNumber, String status);
    
    List<OrderItem> getOrderItemsByOrderNumber(String orderNumber);
    
    int addOrderItem(OrderItem orderItem);
}