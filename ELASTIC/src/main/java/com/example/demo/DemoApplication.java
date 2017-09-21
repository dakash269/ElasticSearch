package com.example.demo;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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
		}
		catch (Exception e){
			System.out.println(e);
		}

	}
}
