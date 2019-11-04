package com.examp.bienajuste;

public class User {
    private String userID;
    private UserInfo info;

    public User(String userID, UserInfo info){
        this.userID=userID;
        this.info=info;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }
}
