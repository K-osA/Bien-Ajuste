package com.examp.bienajuste;

public class User {
    private int cartID;
    private UserInfo info;

    public User( int cartID, UserInfo info){
        this.cartID=cartID;
        this.info=info;
    }




    public int getcartID() { return cartID; }

    public UserInfo getUserInfo() {
        return info;
    }

    public void setUserInfo(UserInfo info) {
        this.info = info;
    }
}
