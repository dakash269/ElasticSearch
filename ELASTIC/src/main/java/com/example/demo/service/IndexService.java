package com.example.demo.service;

import com.example.demo.model.Customer;
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
    public long bulkIndex() throws Exception {
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
//                if (counter % Constants.INDEX_COMMIT_SIZE == 0) {
                    elasticsearchTemplate.bulkIndex(queries);
                    queries.clear();
                    System.out.println("bulkIndex counter : " + counter);
//                }
                counter++;
            }
            if (queries.size() > 0) {
                elasticsearchTemplate.bulkIndex(queries);
            }
//            elasticsearchTemplate.refresh(CUSTOMER_INDEX_NAME, true);
            System.out.println("bulkIndex completed.");
        } catch (Exception e) {
            System.out.println("IndexerService.bulkIndex e;" + e.getMessage());
            throw e;
        }
        return -1;
    }
    private List<Customer> createTestData() {
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer("1","Jack", "Smith"));
        customers.add(new Customer("2","Ram", "Johnson"));
        customers.add(new Customer("3","Bharat", "Smith"));
        customers.add(new Customer("4","Laxman", "Williams"));
        return customers;
    }
}

