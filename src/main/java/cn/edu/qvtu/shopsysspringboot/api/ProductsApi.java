package cn.edu.qvtu.shopsysspringboot.api;

import cn.edu.qvtu.shopsysspringboot.pojo.Products;
import cn.edu.qvtu.shopsysspringboot.service.ProductsService;
import cn.edu.qvtu.shopsysspringboot.util.ApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ProductsApi {

    @Autowired
    ProductsService productsService;

    //查询所有商品
    @GetMapping("/Allproducts")
    public ApiData getAllProducts() {
        return productsService.getAllProducts();
    }

    //管理员添加商品
    @PostMapping("/admin/Addproducts")
    public ApiData AddProducts(@RequestBody Products products){
        return productsService.AddProducts(products);
    }

    //管理员更新商品
    @PutMapping("/admin/Updateproducts")
    public ApiData updateProducts(@RequestBody Products products){
        return productsService.updateProducts(products);
    }

    //管理员删除商品
    @DeleteMapping("/admin/Deleteproducts/{id}")
    public ApiData deleteProducts(@PathVariable int id){
        return productsService.deleteProducts(id);
    }

    //根据分类查询商品
    @GetMapping("/products/category/{category}")
    public ApiData getProductsByCategory(@PathVariable String category){
        return productsService.getProductsByCategory(category);
    }

    //根据商品名搜索商品
    @GetMapping("/products/search/{pname}")
    public ApiData searchProductsByName(@PathVariable String pname){
        return productsService.searchProductsByName(pname);
    }

}
