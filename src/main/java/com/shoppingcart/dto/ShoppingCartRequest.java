package com.shoppingcart.dto;

import com.shoppingcart.model.Item;

public class ShoppingCartRequest {

    private String userId;
    private Item item;

    public ShoppingCartRequest(String userId, Item item) {
        this.userId = userId;
        this.item = item;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
