package com.shoppingcart.service;

import com.shoppingcart.exception.DuplicateKeyException;
import com.shoppingcart.model.User;
import com.shoppingcart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User updateUser(User user) {
        // TODO: Add Try Catch to handle exception
        return userRepository.save(user);
    }

    @Override
    public boolean removeUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<User> getUser(String id) {
        // TODO: Add Try Catch to handle exception
        return userRepository.findById(id);
    }

    @Override
    public User insertUser(User user) {
        try {
            return userRepository.insert(user);
        } catch (org.springframework.dao.DuplicateKeyException e) {
            throw new DuplicateKeyException(e.getMessage());
        }
    }
}
