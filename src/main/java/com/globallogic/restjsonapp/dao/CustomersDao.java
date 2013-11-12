package com.globallogic.restjsonapp.dao;

import com.globallogic.restjsonapp.dto.Customer;
import com.globallogic.restjsonapp.dto.Product;

import java.util.Set;

public interface CustomersDao {

    public Set<Customer> selectAll();
    public Customer selectById(long id);
    public void deleteById(long id);

}
