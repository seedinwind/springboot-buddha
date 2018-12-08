package org.buddha.wise.admin.source.repo;

import org.buddha.wise.admin.source.model.PassageSource;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PassageSourceRepository extends MongoRepository<PassageSource, String> {
    List<PassageSource> findAll();
}
