package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) { return customerRepository.save(customer); }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public void deleteAll(){customerRepository.deleteAll();}

    public Customer findOne(String id) {
        return customerRepository.findOne(id);
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }
    public List<Customer> findByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

}