package com.globallogic.restjsonapp.services;

import com.globallogic.restjsonapp.dao.CustomersDao;
import com.globallogic.restjsonapp.dao.MockCustomersDao;
import com.globallogic.restjsonapp.dto.Customer;

import java.util.Set;

public class CustomersService {

    private CustomersDao customersDao = new MockCustomersDao();

    public Set<Customer> selectAll() {
        return customersDao.selectAll();
    }

    public void deleteById(long id) {
        customersDao.deleteById(id);
    }

    public Customer selectById(long id) {
        return customersDao.selectById(id);
    }
}
