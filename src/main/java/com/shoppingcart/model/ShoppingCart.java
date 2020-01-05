package com.shoppingcart.model;

import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.UUID;

public class ShoppingCart {
    @Id
    private String id;
    private HashMap<String, Item> items = new HashMap<>();
    private Double totalAmount = 0.0;
    private String userId;

    public ShoppingCart(String userId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Item> items) {
        this.items = items;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }
}
