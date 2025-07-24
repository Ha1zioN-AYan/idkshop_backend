package cn.edu.qvtu.shopsysspringboot.mapper;

import cn.edu.qvtu.shopsysspringboot.pojo.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {
    List<Cart> getCartByUsername(@Param("username") String username);
    
    int addToCart(Cart cart);
    
    int updateCartQuantity(Cart cart);
    
    int removeFromCart(int id);
    
    int clearCart(@Param("username") String username);
    
    Cart getCartItem(@Param("username") String username, @Param("productId") int productId);
}