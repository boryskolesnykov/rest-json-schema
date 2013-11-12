package com.globallogic.restjsonapp.dao;

import com.globallogic.restjsonapp.dto.Product;

import java.util.Set;

public interface ProductsDao {

    public Set<Product> selectAll();
    public Product selectById(long id);
    public void deleteById(long id);

}
