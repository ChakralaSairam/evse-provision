package com.sampleProject.evse.provision.repository;

import com.sampleProject.evse.provision.model.Evse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EvseRepo extends MongoRepository<Evse,String> {

    void deleteBySiteId(String siteId);

    List<Evse> findBysiteId(String siteId);


}
