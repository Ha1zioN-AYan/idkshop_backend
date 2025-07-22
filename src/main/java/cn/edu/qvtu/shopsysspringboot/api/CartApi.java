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

    @GetMapping("/cart/{username}")
    public ApiData getCartByUsername(@PathVariable String username) {
        return cartService.getCartByUsername(username);
    }

    @PostMapping("/cart/add")
    public ApiData addToCart(@RequestParam String username,
                            @RequestParam int productId,
                            @RequestParam String productName,
                            @RequestParam double price,
                            @RequestParam String imageurl) {
        return cartService.addToCart(username, productId, productName, price, imageurl);
    }

    @PutMapping("/cart/update")
    public ApiData updateCartQuantity(@RequestParam int id, @RequestParam int quantity) {
        return cartService.updateCartQuantity(id, quantity);
    }

    @DeleteMapping("/cart/remove/{id}")
    public ApiData removeFromCart(@PathVariable int id) {
        return cartService.removeFromCart(id);
    }

    @DeleteMapping("/cart/clear/{username}")
    public ApiData clearCart(@PathVariable String username) {
        return cartService.clearCart(username);
    }
}