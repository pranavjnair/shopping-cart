package com.shoppingcart.model;

public class Item {

    private String itemId;
    private Double price;
    private Integer quantity;

    public Item(){
    }

    public Item(String itemId, Double price){
        this.itemId = itemId;
        this.price = price;
        this.quantity = 1;
    }

    public Item(String itemId, Double price, Integer quantity){
        this.itemId = itemId;
        this.price = price;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
