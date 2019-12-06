package com.examp.bienajuste;

public class User {
    private String userID;
    private int cartID;
    private UserInfo info;

    public User(String userID, int cartID, UserInfo info){
        this.cartID=cartID;
        this.userID=userID;
        this.info=info;
    }

    public String getUserID() {
        return userID;
    }

    public int getcartID() { return cartID; }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public UserInfo getUserInfo() {
        return info;
    }

    public void setUserInfo(UserInfo info) {
        this.info = info;
    }
}
