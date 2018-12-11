package org.buddha.wise.admin.dest.repo;

import org.buddha.wise.admin.dest.model.TranslateTask;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TranslateRepository extends MongoRepository<TranslateTask,String> {
}
