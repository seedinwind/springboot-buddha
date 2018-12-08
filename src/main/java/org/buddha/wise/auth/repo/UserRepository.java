package org.buddha.wise.auth.repo;

import org.buddha.wise.auth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends MongoRepository<User,String>{
    User findByName(@Param("name") String name);
}