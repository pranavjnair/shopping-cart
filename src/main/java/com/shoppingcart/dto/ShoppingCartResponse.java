package com.shoppingcart.dto;

import com.shoppingcart.model.Item;
import com.shoppingcart.model.ShoppingCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartResponse {
    private String id;
    private List<Item> items = new ArrayList<>();
    private Double totalAmount = 0.0;
    private String userId;

    public ShoppingCartResponse(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
        this.totalAmount = shoppingCart.getTotalAmount();
        this.userId = shoppingCart.getUserId();
        this.items = shoppingCart.getItems().values().stream().collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
