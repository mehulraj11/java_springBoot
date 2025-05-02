package com.project.myproject;

public class WishlistDTO {
    private String userName;
    private String email;
    private String description;
    private String productName;
    private double price;

    public WishlistDTO(User user, Product product) {
        this.userName = user.getName();
        this.productName = product.getName();
        this.email = user.getEmail();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
