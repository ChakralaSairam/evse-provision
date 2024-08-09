package com.sampleProject.evse.provision.service;

import com.sampleProject.evse.provision.model.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;


    public long generateSequence(String seqName) {

        DatabaseSequence counter = mongoOperations.findOne(query(where("_id").is(seqName)),
                DatabaseSequence.class);

//        !(Objects.equals(counter.getSeq(), BigInteger.valueOf(1))) ?: counter.setSeq(BigInteger.valueOf(10000000));

        if(Objects.isNull(counter)){
            mongoOperations.upsert(query(where("_id").is(seqName)),
                    new Update().set("seq",10000000),DatabaseSequence.class);
        }else{
            mongoOperations.upsert(query(where("_id").is(seqName)),
                    new Update().inc("seq",1),DatabaseSequence.class);
        }

        return mongoOperations.findOne(query(where("_id").is(seqName)),
                DatabaseSequence.class).getSeq();
    }
}
