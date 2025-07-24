package cn.edu.qvtu.shopsysspringboot.mapper;

import cn.edu.qvtu.shopsysspringboot.pojo.Order;
import cn.edu.qvtu.shopsysspringboot.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> getOrdersByUsername(@Param("username") String username);
    
    Order getOrderByOrderNumber(@Param("orderNumber") String orderNumber);
    
    int createOrder(Order order);
    
    int updateOrderStatus(@Param("orderNumber") String orderNumber, @Param("status") String status);
    
    List<OrderItem> getOrderItemsByOrderNumber(@Param("orderNumber") String orderNumber);
    
    int addOrderItem(OrderItem orderItem);
}