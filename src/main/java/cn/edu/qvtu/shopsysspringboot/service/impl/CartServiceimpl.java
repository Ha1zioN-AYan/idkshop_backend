package cn.edu.qvtu.shopsysspringboot.service.impl;

import cn.edu.qvtu.shopsysspringboot.mapper.CartMapper;
import cn.edu.qvtu.shopsysspringboot.pojo.Cart;
import cn.edu.qvtu.shopsysspringboot.service.CartService;
import cn.edu.qvtu.shopsysspringboot.util.ApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CartServiceimpl implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Override
    public ApiData getCartByUsername(String username) {
        if(StringUtils.isEmpty(username)) {
            return ApiData.fail("用户名为空");
        }
        List<Cart> cartList = cartMapper.getCartByUsername(username);
        return ApiData.success(cartList);
    }

    @Override
    public ApiData addToCart(String username, int productId, String productName, double price, String imageurl) {
        if(StringUtils.isEmpty(username)) {
            return ApiData.fail("用户名为空");
        }
        if(productId <= 0) {
            return ApiData.fail("商品ID无效");
        }
        if(StringUtils.isEmpty(productName)) {
            return ApiData.fail("商品名称为空");
        }
        if(price <= 0) {
            return ApiData.fail("商品价格无效");
        }
        
        Cart existingItem = cartMapper.getCartItem(username, productId);
        if(existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            int i = cartMapper.updateCartQuantity(existingItem);
            if(i > 0) {
                return ApiData.success("商品数量已更新");
            } else {
                return ApiData.fail("更新失败");
            }
        } else {
            Cart cart = new Cart();
            cart.setUsername(username);
            cart.setProductId(productId);
            cart.setProductName(productName);
            cart.setPrice(price);
            cart.setQuantity(1);
            cart.setImageurl(imageurl);
            
            int i = cartMapper.addToCart(cart);
            if(i > 0) {
                return ApiData.success("添加到购物车成功");
            } else {
                return ApiData.fail("添加失败");
            }
        }
    }

    @Override
    public ApiData updateCartQuantity(int id, int quantity) {
        if(id <= 0) {
            return ApiData.fail("购物车项ID无效");
        }
        if(quantity <= 0) {
            return ApiData.fail("商品数量无效");
        }
        
        Cart cart = new Cart();
        cart.setId(id);
        cart.setQuantity(quantity);
        
        int i = cartMapper.updateCartQuantity(cart);
        if(i > 0) {
            return ApiData.success("数量更新成功");
        } else {
            return ApiData.fail("更新失败");
        }
    }

    @Override
    public ApiData removeFromCart(int id) {
        if(id <= 0) {
            return ApiData.fail("购物车项ID无效");
        }
        
        int i = cartMapper.removeFromCart(id);
        if(i > 0) {
            return ApiData.success("移除成功");
        } else {
            return ApiData.fail("移除失败");
        }
    }

    @Override
    public ApiData clearCart(String username) {
        if(StringUtils.isEmpty(username)) {
            return ApiData.fail("用户名为空");
        }
        
        int i = cartMapper.clearCart(username);
        if(i > 0) {
            return ApiData.success("购物车已清空");
        } else {
            return ApiData.fail("清空失败");
        }
    }
}