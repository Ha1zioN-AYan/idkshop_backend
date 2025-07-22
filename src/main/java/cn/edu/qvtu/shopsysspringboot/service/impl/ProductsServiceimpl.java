package cn.edu.qvtu.shopsysspringboot.service.impl;

import cn.edu.qvtu.shopsysspringboot.mapper.ProductsMapper;
import cn.edu.qvtu.shopsysspringboot.pojo.Products;
import cn.edu.qvtu.shopsysspringboot.service.ProductsService;
import cn.edu.qvtu.shopsysspringboot.util.ApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class ProductsServiceimpl implements ProductsService {

    @Autowired
    ProductsMapper productsMapper;


    @Override
    public ApiData getAllProducts() {
        //查询所有商品
        List<Products> products = productsMapper.getAllProducts();
        return ApiData.success(products);
    }

    @Override
    public ApiData AddProducts(Products products) {
        if(StringUtils.isEmpty(products.getPname())) {
            return ApiData.fail("商品名称为空");
        }
        if(StringUtils.isEmpty(products.getPrice())) {
            return ApiData.fail("商品价格为空");
        }
        if(StringUtils.isEmpty(products.getImageurl())) {
            return ApiData.fail("商品图片为空");
        }
        if(StringUtils.isEmpty(products.getCategory())) {
            return ApiData.fail("商品分类为空");
        }
        int i = productsMapper.AddProducts(products);
        if(i > 0) {
            return ApiData.success("添加成功");
        }
        else {
            return ApiData.fail("添加失败");
        }
    }

    @Override
    public ApiData updateProducts(Products products) {
        if(products.getId() <= 0) {
            return ApiData.fail("商品ID无效");
        }
        if(StringUtils.isEmpty(products.getPname())) {
            return ApiData.fail("商品名称为空");
        }
        if(StringUtils.isEmpty(products.getPrice())) {
            return ApiData.fail("商品价格为空");
        }
        if(StringUtils.isEmpty(products.getImageurl())) {
            return ApiData.fail("商品图片为空");
        }
        if(StringUtils.isEmpty(products.getCategory())) {
            return ApiData.fail("商品分类为空");
        }
        int i = productsMapper.updateProducts(products);
        if(i > 0) {
            return ApiData.success("更新成功");
        }
        else {
            return ApiData.fail("更新失败");
        }
    }

    @Override
    public ApiData deleteProducts(int id) {
        if(id <= 0) {
            return ApiData.fail("商品ID无效");
        }
        int i = productsMapper.deleteProducts(id);
        if(i > 0) {
            return ApiData.success("删除成功");
        }
        else {
            return ApiData.fail("删除失败");
        }
    }

    @Override
    public ApiData getProductsByCategory(String category) {
        if(StringUtils.isEmpty(category)) {
            return ApiData.fail("商品分类为空");
        }
        List<Products> products = productsMapper.getProductsByCategory(category);
        return ApiData.success(products);
    }

    @Override
    public ApiData searchProductsByName(String pname) {
        if(StringUtils.isEmpty(pname)) {
            return ApiData.fail("商品名称为空");
        }
        List<Products> products = productsMapper.searchProductsByName(pname);
        return ApiData.success(products);
    }
}
