package com.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class App{

    private static Logger log = LoggerFactory.getLogger(App.class);

    /*
    @Autowired
    private UserDatabaseRepository userDatabaseRepository;
    */

    public static void main(String[] args) {
        log.info("[starting server]");
        SpringApplication.run(App.class, args);
        log.info("[server started]");
    }
}
