package com.crio.starter.utils;

import com.crio.starter.data.XmemeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

	@Autowired
    private MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence() {
        long count = mongoOperations.count(new Query(), XmemeEntity.class);
        return (count + 1);
    }

}
