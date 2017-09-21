package com.example.demo;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
   @Autowired
   private CustomerService customerService;
	@Autowired
	private IndexService indexService;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		try {
             indexService.bulkIndex();
//			customerService.save(new Customer("1","Jack", "Smith"));
//			customerService.save(new Customer("2","Ram", "Johnson"));
//			customerService.save(new Customer("3","Bharat", "Smith"));
//			customerService.save(new Customer("4","Laxman", "Williams"));
//			Iterable<Customer> customers = customerService.findAll();
//			for (Object aCustomers : customers) {
//				System.out.println(aCustomers);
//			}
		}
		catch (Exception e){
			System.out.println(e);
		}

	}
}
