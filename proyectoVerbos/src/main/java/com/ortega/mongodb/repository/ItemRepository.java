package com.ortega.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ortega.mongodb.model.GroceryItem;

@Repository
public class ItemRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ItemRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void save(GroceryItem groceryItem) {
        mongoTemplate.save(groceryItem);
    }

    public List<GroceryItem> findAll() {
        return mongoTemplate.findAll(GroceryItem.class);
    }

    public Optional<GroceryItem> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, GroceryItem.class));
    }

    public void deleteById(String id) {
        mongoTemplate.remove(findById(id).orElse(null));
    }

    public boolean existsById(String id) {
        return mongoTemplate.exists(Query.query(Criteria.where("_id").is(id)), GroceryItem.class);
    }
}