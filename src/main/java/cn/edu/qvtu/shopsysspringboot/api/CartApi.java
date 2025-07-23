package cn.edu.qvtu.shopsysspringboot.api;

import cn.edu.qvtu.shopsysspringboot.service.CartService;
import cn.edu.qvtu.shopsysspringboot.util.ApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CartApi {

    @Autowired
    CartService cartService;

    // 根据用户名获取购物车内容
    @GetMapping("/cart/{username}")
    public ApiData getCartByUsername(@PathVariable String username) {
        return cartService.getCartByUsername(username);
    }

    // 添加商品到购物车
    @PostMapping("/cart/add")
    public ApiData addToCart(@RequestParam String username,
                            @RequestParam int productId,
                            @RequestParam String productName,
                            @RequestParam double price,
                            @RequestParam String imageurl) {
        return cartService.addToCart(username, productId, productName, price, imageurl);
    }

    // 更新购物车商品数量
    @PutMapping("/cart/update")
    public ApiData updateCartQuantity(@RequestParam int id, @RequestParam int quantity) {
        return cartService.updateCartQuantity(id, quantity);
    }

    // 从购物车移除指定商品
    @DeleteMapping("/cart/remove/{id}")
    public ApiData removeFromCart(@PathVariable int id) {
        return cartService.removeFromCart(id);
    }

    // 清空用户购物车
    @DeleteMapping("/cart/clear/{username}")
    public ApiData clearCart(@PathVariable String username) {
        return cartService.clearCart(username);
    }
}