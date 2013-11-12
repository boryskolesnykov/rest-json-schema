package com.globallogic.restjsonapp.dao;

import com.globallogic.restjsonapp.dto.Customer;
import com.globallogic.restjsonapp.dto.Product;

import java.util.HashSet;
import java.util.Set;

public class MockCustomersDao implements CustomersDao {

    private static Set<Customer> customers = new HashSet<>();

    static {
        customers.add(new Customer(1L, "John", "Doe", "x@y.z", 40));
        customers.add(new Customer(2L, "Sherlock", "Holmes", "x@y.z", 40));
        customers.add(new Customer(1L, "John", "Watson", "x@y.z", 40));
    }

    @Override
    public Set<Customer> selectAll() {
        return customers;
    }

    @Override
    public Customer selectById(long id) {
        for (Customer customer : customers){
            if (customer.getId() == id)
                return customer;
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
        for (Customer customer : customers){
            if (customer.getId() == id)
                customers.remove(customer);
        }
    }
}
