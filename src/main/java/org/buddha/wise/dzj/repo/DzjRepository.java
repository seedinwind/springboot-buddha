package org.buddha.wise.dzj.repo;

import org.buddha.wise.dzj.model.Dzj;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DzjRepository extends MongoRepository<Dzj,String>{
    List<Dzj> findByJingti(@Param("jingti") String name);
}
