package com.sampleProject.evse.provision.repository;

import com.sampleProject.evse.provision.model.Site;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


public interface SiteRepo extends MongoRepository<Site, BigInteger> {


}
