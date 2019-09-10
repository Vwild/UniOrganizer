package com.example.uniorganizer.Friendtransaction;

public class User {

     private String email;
     private String username;
     private boolean presentAtUni ;

    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isPresentAtUni() {
        return presentAtUni;
    }

    public void setPresentAtUni(boolean presentAtUni) {
        this.presentAtUni = presentAtUni;
    }
}
