package cn.edu.qvtu.shopsysspringboot.api;

import cn.edu.qvtu.shopsysspringboot.pojo.Products;
import cn.edu.qvtu.shopsysspringboot.service.ProductsService;
import cn.edu.qvtu.shopsysspringboot.util.ApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
public class ProductsApi {

    @Autowired
    ProductsService productsService;

    @Value("${file.upload.path:/uploads/}")
    private String uploadPath;

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

    //图片上传接口
    @PostMapping("/admin/upload")
    public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            if (file.isEmpty()) {
                response.put("success", false);
                response.put("message", "文件不能为空");
                return ResponseEntity.badRequest().body(response);
            }

            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                response.put("success", false);
                response.put("message", "文件名不能为空");
                return ResponseEntity.badRequest().body(response);
            }

            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String allowedExtensions = ".jpg,.jpeg,.png,.gif,.bmp";
            if (!allowedExtensions.contains(fileExtension.toLowerCase())) {
                response.put("success", false);
                response.put("message", "只支持图片格式: jpg, jpeg, png, gif, bmp");
                return ResponseEntity.badRequest().body(response);
            }

            String fileName = UUID.randomUUID().toString().replaceAll("-","") + fileExtension;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File destFile = new File(uploadDir, fileName);
            file.transferTo(destFile);

            String imageUrl = "/uploads/" + fileName;
            response.put("success", true);
            response.put("message", "图片上传成功");
            response.put("imageUrl", imageUrl);
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "图片上传失败: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

}
