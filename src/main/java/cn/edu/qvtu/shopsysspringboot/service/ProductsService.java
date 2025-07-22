package cn.edu.qvtu.shopsysspringboot.service;

import cn.edu.qvtu.shopsysspringboot.pojo.Products;
import cn.edu.qvtu.shopsysspringboot.util.ApiData;

public interface ProductsService {
    ApiData getAllProducts();

    ApiData AddProducts(Products products);

    ApiData updateProducts(Products products);

    ApiData deleteProducts(int id);

    ApiData getProductsByCategory(String category);

    ApiData searchProductsByName(String pname);
}
