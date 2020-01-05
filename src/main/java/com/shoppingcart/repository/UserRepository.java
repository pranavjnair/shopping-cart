package com.shoppingcart.repository;


import com.shoppingcart.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, java.lang.String> {
}
