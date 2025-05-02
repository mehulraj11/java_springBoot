package com.project.myproject;

public class UserDTO {

    private String name;
    private String email;

    public UserDTO() {
        // Default constructor (optional but useful for Jackson)
    }

    public UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
