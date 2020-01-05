package com.shoppingcart.controller;

import com.shoppingcart.App;
import com.shoppingcart.model.User;
import com.shoppingcart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(App.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") String id) {
        log.info("Getting user with username: {}.", id);
        Optional<User> optionalUser = userService.getUser(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            log.info("No user with username: {}.", id);
            return null;
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public User insertUser(@RequestBody User user) {
        log.info("Inserting user with ID: {}.", user);
        return userService.insertUser(user);
    }

}