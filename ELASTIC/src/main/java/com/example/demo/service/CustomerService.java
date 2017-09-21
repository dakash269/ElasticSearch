package com.example.demo.service;

import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer);

    void delete(Customer customer);

    void deleteAll();

    Customer findOne(String id);

    Iterable<Customer> findAll();

    List<Customer> findByLastName(String lastName);
}