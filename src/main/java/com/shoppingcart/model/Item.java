package com.shoppingcart.model;

public class Item {

    private String itemId;
    private Double price;
    private int quantity;

    public Item(String itemId, Double price){
        this.itemId = itemId;
        this.price = price;
        this.quantity = 1;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
