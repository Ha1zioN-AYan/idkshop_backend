package cn.edu.qvtu.shopsysspringboot.mapper;

import cn.edu.qvtu.shopsysspringboot.pojo.Products;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductsMapper {
    List<Products> getAllProducts();

    int AddProducts(Products products);

    int updateProducts(Products products);

    int deleteProducts(int id);

    List<Products> getProductsByCategory(String category);

    List<Products> searchProductsByName(String pname);
}
