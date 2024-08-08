package com.sampleProject.evse.provision.repository;

import com.sampleProject.evse.provision.model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public class EvseCustomRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    public void retireEvse(String evseId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("evseId").is(evseId));
        Update update=new Update();
        update.set("isRetired",true);
        Site site = mongoTemplate.findAndModify(query,update,Site.class);

    }
}
