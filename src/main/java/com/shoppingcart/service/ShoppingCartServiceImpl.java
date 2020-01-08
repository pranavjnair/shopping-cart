package com.shoppingcart.service;

import com.shoppingcart.exception.NotFoundException;
import com.shoppingcart.model.Item;
import com.shoppingcart.model.ShoppingCart;
import com.shoppingcart.model.User;
import com.shoppingcart.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserService userService){
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
    }

    @Override
    public ShoppingCart insertItem(String userId, Item item) {
        Optional<User> optionalUser = userService.getUser(userId);
        if (optionalUser.isPresent()) { // user is present
            User user = optionalUser.get();
            if (user.getShoppingCartId() != null) { // if shopping cart exists
                return addItemToExistingCart(item, user);
            } else { // if shopping cart doesn't exist
                return addItemToNewCart(userId, item, user);
            }
        } else { // user doesn't exist
            throw new NotFoundException("User Not Found");
        }
    }

    private ShoppingCart addItemToNewCart(String userId, Item item, User user) {
        ShoppingCart shoppingCart = new ShoppingCart(userId);
        shoppingCart.getItems().put(item.getItemId(), item);
        shoppingCart.setTotalAmount(item.getPrice() * item.getQuantity());
        shoppingCartRepository.insert(shoppingCart); // inserts to mongodb
        user.setShoppingCartId(shoppingCart.getId());
        userService.updateUser(user); // updates user in mongodb
        return shoppingCart;
    }

    private ShoppingCart addItemToExistingCart(Item item, User user) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(user.getShoppingCartId()).get();
        HashMap<String, Item> items = shoppingCart.getItems();
        if (items.containsKey(item.getItemId())) { // if item exists in shopping cart
            Item updatedItem = items.get(item.getItemId());
            updatedItem.setQuantity(updatedItem.getQuantity() + item.getQuantity());
            shoppingCart.setTotalAmount(shoppingCart.getTotalAmount() + (item.getPrice() * item.getQuantity()));
            updateShoppingCardRepository(shoppingCart, items, updatedItem);
        } else { // if item doesn't exist in shopping cart
            items.put(item.getItemId(), item);
            shoppingCart.setTotalAmount(shoppingCart.getTotalAmount() + (item.getPrice() * item.getQuantity()));
            shoppingCartRepository.save(shoppingCart); // save to mongodb
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart removeItem(String userId, Item item) {
        Optional<User> optionalUser = userService.getUser(userId);
        if (!optionalUser.isPresent()) { // user doesn't exist
            throw new NotFoundException("User Not Found");
        }

        User user = optionalUser.get();
        if (user.getShoppingCartId() == null) { // shopping cart doesn't exist
            throw new NotFoundException("Shopping Cart Not Found");
        }

        ShoppingCart shoppingCart = shoppingCartRepository.findById(user.getShoppingCartId()).get();
        HashMap<String, Item> items = shoppingCart.getItems();
        if (items.containsKey(item.getItemId())) { // item exists
            Item updatedItem = items.get(item.getItemId());
            if (item.getQuantity() > updatedItem.getQuantity()) { // if the quantity to remove is greater than current quantity
                shoppingCart.setTotalAmount(shoppingCart.getTotalAmount() - (updatedItem.getQuantity() * updatedItem.getPrice()));
                updatedItem.setQuantity(0);
                updateShoppingCardRepository(shoppingCart, items, updatedItem);
            } else { // if the quantity to remove is okay
                shoppingCart.setTotalAmount(shoppingCart.getTotalAmount() - (item.getQuantity() * item.getPrice()));
                updatedItem.setQuantity(updatedItem.getQuantity() - item.getQuantity());
                updateShoppingCardRepository(shoppingCart, items, updatedItem);
            }
        } else { // item doesn't exist
            throw new NotFoundException("Item Not Found");
        }
        return shoppingCart;
    }


    private void updateShoppingCardRepository(ShoppingCart shoppingCart, HashMap<String, Item> items, Item updatedItem) {
        items.replace(updatedItem.getItemId(), updatedItem);
        shoppingCart.setItems(items);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> getShoppingCart(String id) {
        return shoppingCartRepository.findById(id);
    }
}
