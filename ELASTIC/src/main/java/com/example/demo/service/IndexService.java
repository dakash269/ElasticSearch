package com.example.demo.service;

import com.example.demo.model.Customer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexService {
    private  static  final  String CUSTOMER_INDEX_NAME = "customer_index";
    private  static  final  String CUSTOMER_INDEX_TYPE = "customer_type";
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    public  void bulkIndex() throws Exception {
        int counter = 0;
        try {
            if (!elasticsearchTemplate.indexExists(CUSTOMER_INDEX_NAME)) {
                elasticsearchTemplate.createIndex(CUSTOMER_INDEX_NAME);
            }
             Gson gson = new Gson();
            List<IndexQuery> queries = new ArrayList<IndexQuery>();

            List<Customer> customers = createTestData();
            for (Customer customer : customers) {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(customer.getId());
                indexQuery.setSource(gson.toJson(customer));
                indexQuery.setIndexName(CUSTOMER_INDEX_NAME);
                indexQuery.setType(CUSTOMER_INDEX_TYPE);
                queries.add(indexQuery);
                counter++;
            }
            elasticsearchTemplate.bulkIndex(queries);
            queries.clear();
        } catch (Exception e) {
            System.out.println("IndexerService.bulkIndex e;" + e.getMessage());
            throw e;
        }
    };
    private List<Customer> createTestData() {
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer("34","ab", "Smith"));
        customers.add(new Customer("35","cd", "Johnson"));
        customers.add(new Customer("36","ef", "Smith"));
        customers.add(new Customer("37","gh", "Williams"));
        customers.add(new Customer("38","ij", "po"));
        customers.add(new Customer("39","kl", "pio"));
        return customers;
    }
}

