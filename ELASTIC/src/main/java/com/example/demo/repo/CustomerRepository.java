package com.example.demo.repo;

import com.example.demo.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
	List<Customer> findByLastName(String lastName);
	List<Customer> findByFirstName(String firstName);
}
