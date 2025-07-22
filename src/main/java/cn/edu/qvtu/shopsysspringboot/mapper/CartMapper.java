package cn.edu.qvtu.shopsysspringboot.mapper;

import cn.edu.qvtu.shopsysspringboot.pojo.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    List<Cart> getCartByUsername(String username);
    
    int addToCart(Cart cart);
    
    int updateCartQuantity(Cart cart);
    
    int removeFromCart(int id);
    
    int clearCart(String username);
    
    Cart getCartItem(String username, int productId);
}