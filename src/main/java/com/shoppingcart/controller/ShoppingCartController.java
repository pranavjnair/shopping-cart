package com.shoppingcart.controller;

import com.shoppingcart.App;
import com.shoppingcart.dto.ShoppingCartResponse;
import com.shoppingcart.model.ShoppingCart;
import com.shoppingcart.dto.ShoppingCartRequest;
import com.shoppingcart.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class ShoppingCartController {
    private static Logger log = LoggerFactory.getLogger(App.class);

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ShoppingCart getShoppingCart(@PathVariable("id") String id){
        log.info("Getting shopping cart with username: {}.", id);
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartService.getShoppingCart(id);
        if (optionalShoppingCart.isPresent()){
            return optionalShoppingCart.get();
        } else {
            log.info("No shopping cart with id: {}.", id);
            return null;
        }
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ShoppingCartResponse addItemToShoppingCart(@RequestBody ShoppingCartRequest shoppingCartRequest){
        ShoppingCart shoppingCart = shoppingCartService.insertItem(shoppingCartRequest.getUserId(),shoppingCartRequest.getItem());
        ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse(shoppingCart);
        return shoppingCartResponse;
    }


}
