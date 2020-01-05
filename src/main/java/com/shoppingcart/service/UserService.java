package com.shoppingcart.service;

import com.shoppingcart.model.User;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(String id);
    User insertUser(User user);
    User updateUser(User user);
    boolean removeUser(String id);
}