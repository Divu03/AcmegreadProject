package com.example.shopping;

public class Product {
    private int id;
    private int imageResource;
    private String name;
    private String description;
    private double price;
    private boolean isInCart;
    private boolean addedToCart;

    public Product(int imageResource, String name, String description, double price) {
        this.imageResource = imageResource;
        this.name = name;
        this.description = description;
        this.price = price;
        isInCart = false;
        this.id = id;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }
    public boolean isInCart() {
        return isInCart;
    }

    public void setInCart(boolean inCart) {
        isInCart = inCart;
    }
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
    public boolean isAddedToCart() {
        return addedToCart;
    }

    public void setAddedToCart(boolean addedToCart) {
        this.addedToCart = addedToCart;
    }
}
