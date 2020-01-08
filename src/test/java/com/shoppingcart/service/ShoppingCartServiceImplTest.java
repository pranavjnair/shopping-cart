package com.shoppingcart.service;

import com.shoppingcart.model.Item;
import com.shoppingcart.model.ShoppingCart;
import com.shoppingcart.model.User;
import com.shoppingcart.repository.ShoppingCartRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class ShoppingCartServiceImplTest {

    ShoppingCartServiceImpl shoppingCartService;


    ShoppingCartRepository shoppingCartRepository = mock(ShoppingCartRepository.class);
    ;

    UserService userService = mock(UserService.class);

    User user1 = new User("pranav", "Pranav", "Nair");

    @BeforeEach
    void refresh() {
        when(userService.getUser(user1.getId())).thenReturn(Optional.of(user1));
        shoppingCartService = new ShoppingCartServiceImpl(shoppingCartRepository, userService);
    }

    @Test
    void insertItemWithDefaultQuantity() {
        Item item1 = new Item("banana", 4.00);
        ShoppingCart shoppingCart = shoppingCartService.insertItem("pranav", item1);
        int itemQuantity = shoppingCart.getItems().get(item1.getItemId()).getQuantity();
        Assert.assertEquals(1, itemQuantity);
    }

    @Test
    void insertItemWithMultipleQuantity() {
        Item item1 = new Item("banana", 4.00, 2);
        ShoppingCart shoppingCart = shoppingCartService.insertItem("pranav", item1);
        int itemQuantity = shoppingCart.getItems().get(item1.getItemId()).getQuantity();
        Assert.assertEquals(2, itemQuantity);
        Assert.assertEquals(8.0, shoppingCart.getTotalAmount().doubleValue(), 0.0);
    }

    @Test
    void insertMultipleItems() {
        Item item1 = new Item("banana", 4.00, 2);
        ShoppingCart shoppingCart = shoppingCartService.insertItem("pranav", item1);
        int itemQuantity = shoppingCart.getItems().get(item1.getItemId()).getQuantity();
        Assert.assertEquals(2, itemQuantity);
    }

    @Test
    void removeItem() {

    }

    @Test
    void insertShoppingCart() {
    }

    @Test
    void getShoppingCart() {
    }
}