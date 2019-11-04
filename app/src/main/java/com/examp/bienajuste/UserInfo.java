package com.examp.bienajuste;

public class UserInfo {
    private String userPassword;
    private String userName;
    private int userAge;
    private String userEmail;
    private String userAddress;
    private int userFootsize;
    private int userGender;

    public UserInfo(String userPassword, String userName, int userAge, String userEmail, String userAddress, int userFootsize, int userGender){
        this.userPassword=userPassword;
        this.userName=userName;
        this.userAge=userAge;
        this.userEmail=userEmail;
        this.userAddress=userAddress;
        this.userFootsize=userFootsize;
        this.userGender=userGender;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public int getUserFootsize() {
        return userFootsize;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserFootsize(int userFootsize) {
        this.userFootsize = userFootsize;
    }
}
