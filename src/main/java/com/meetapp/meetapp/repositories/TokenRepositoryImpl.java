package com.meetapp.meetapp.repositories;

import com.meetapp.meetapp.MeetappApplication;
import com.meetapp.meetapp.models.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepositoryImpl implements TokenRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Token getTokenByUserEmail(String userEmail) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userEmail").is(userEmail));
        Token token = mongoTemplate.findOne(query, Token.class);
        MeetappApplication.logger.debug("Get token for "+ token.getUserEmail()+": " + token.getToken());
        return token;
    }

    @Override
    public Token saveTokenByUserEmail(String userEmail, String token) {
        Token tempToken = getTokenByUserEmail(userEmail);
        if(tempToken == null){
            tempToken = new Token();
            tempToken.setUserEmail(userEmail);
        }
        tempToken.setToken(token);
        mongoTemplate.save(tempToken);
        return tempToken;
    }


}
