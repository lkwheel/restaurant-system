package com.codiecode.order.service;

import com.codiecode.order.entity.Sequence;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class DbSeeder implements CommandLineRunner {

    private final MongoOperations mongoOperations;

    public DbSeeder(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void run(String... args) {
        // Initialize the sequence for orders if it doesn't exist
        Query query = new Query(Criteria.where("_id").is("order_sequence"));
        if (!mongoOperations.exists(query, "sequence")) {
            Sequence sequence = new Sequence();
            sequence.setId("order_sequence");
            sequence.setSequence(1000); // Start IDs from 1000
            mongoOperations.save(sequence);
            System.out.println("Initialized order_sequence collection.");
        }
    }
}
