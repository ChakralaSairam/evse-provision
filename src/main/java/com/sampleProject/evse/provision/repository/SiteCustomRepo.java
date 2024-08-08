package com.sampleProject.evse.provision.repository;

import com.sampleProject.evse.provision.model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class SiteCustomRepo {

    @Autowired
    MongoTemplate mongoTemplate;


    public void decreaseEvseCount(String siteId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("siteId").is(siteId));
        Update update=new Update();
        update.inc("evseCount",-1);
        Site site = mongoTemplate.findAndModify(query,update,Site.class);

    }

    public void increaseEvseCount(String siteId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("siteId").is(siteId));
        Update update=new Update();
        update.inc("evseCount",1);
        Site site = mongoTemplate.findAndModify(query,update,Site.class);
    }
}
