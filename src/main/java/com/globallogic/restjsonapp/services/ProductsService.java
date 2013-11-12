package com.globallogic.restjsonapp.services;

import com.globallogic.restjsonapp.dao.MockProductsDao;
import com.globallogic.restjsonapp.dao.ProductsDao;
import com.globallogic.restjsonapp.dto.Product;

import java.util.Set;

public class ProductsService {

    private ProductsDao productsDao = new MockProductsDao();

    public Set<Product> selectAll() {
        return productsDao.selectAll();
    }

    public void deleteById(long id) {
        productsDao.deleteById(id);
    }

    public Product selectById(long id) {
        return productsDao.selectById(id);
    }
}
