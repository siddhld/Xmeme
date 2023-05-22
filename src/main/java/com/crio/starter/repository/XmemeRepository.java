package com.crio.starter.repository;

import com.crio.starter.data.XmemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XmemeRepository extends MongoRepository<XmemeEntity, Long> {
    XmemeEntity findByNameAndUrlAndCaption(String name, String url, String caption);
}
