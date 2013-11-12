package com.globallogic.restjsonapp.dao;

import com.globallogic.restjsonapp.dto.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MockProductsDao implements ProductsDao {

    private static Set<Product> products = new HashSet<>();

    static {
        products.add(new Product(1L, "p1", 5.50));
        products.add(new Product(2L, "p2", 6.50));
        products.add(new Product(3L, "p3", 7.50));
    }

    @Override
    public Set<Product> selectAll() {
        return products;  //TODO: realize
    }

    @Override
    public Product selectById(long id) {
        for (Product product : products){
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
        for (Product product : products){
            if (product.getId() == id)
                products.remove(product);
        }
    }
}
