package com.ankilla.order.service;

import com.ankilla.order.entity.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class SequenceGenerator {

    @Autowired
    private MongoOperations mongoOperations;

    public int generateNextOrderId(){
       Sequence counter = mongoOperations.findAndModify(
                Query.query(where("_id").is("sequence")),
                new Update().inc("sequence",1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                Sequence.class);
        assert counter != null;
        return counter.getSequence();
    }
}
