package cn.edu.qvtu.shopsysspringboot.service;

import cn.edu.qvtu.shopsysspringboot.pojo.Cart;
import cn.edu.qvtu.shopsysspringboot.util.ApiData;

public interface CartService {
    ApiData getCartByUsername(String username);
    
    ApiData addToCart(String username, int productId, String productName, double price, String imageurl);
    
    ApiData updateCartQuantity(int id, int quantity);
    
    ApiData removeFromCart(int id);
    
    ApiData clearCart(String username);
}