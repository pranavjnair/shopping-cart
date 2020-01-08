package com.shoppingcart.service;

import com.shoppingcart.model.Item;
import com.shoppingcart.model.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartService {
    ShoppingCart insertItem(String userId, Item item);
    ShoppingCart removeItem(String userId, Item item);
    Optional<ShoppingCart> getShoppingCart(String id);
}
