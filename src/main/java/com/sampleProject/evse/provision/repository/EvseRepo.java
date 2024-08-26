package com.sampleProject.evse.provision.repository;

import com.sampleProject.evse.provision.model.Evse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;


public interface EvseRepo extends MongoRepository<Evse,String> {

    void deleteBySiteId(BigInteger siteId);

    List<Evse> findBysiteId(BigInteger siteId);

    List<Evse> findAllByIsRetiredAndSerialNumber(boolean isRetired, String serialNumber);

    boolean existsBySerialNumber(String serialNumber);

    Evse findBySerialNumber(String serialNumber);
}
