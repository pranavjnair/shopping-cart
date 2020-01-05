package com.shoppingcart.service;

import com.shoppingcart.model.Item;
import com.shoppingcart.model.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartService {
    void insertItem(String userId, Item item);
    void removeItem(String userId, Item item);
    ShoppingCart insertShoppingCart(String userId, String id);
    Optional<ShoppingCart> getShoppingCart(String id);
}
