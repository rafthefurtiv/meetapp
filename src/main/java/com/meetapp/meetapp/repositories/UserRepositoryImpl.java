package com.meetapp.meetapp.repositories;

import com.meetapp.meetapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User getUserById(Integer userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        return mongoTemplate.findOne(query, User.class);
    }
}
