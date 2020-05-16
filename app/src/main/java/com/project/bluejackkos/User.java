package com.project.bluejackkos;

public class User {
    private String userName;
    private String userPassword;
    private String userPhonenumber;
    private String userGender;
    private String userDob;
    private String userId;

    public User(String userName, String userPassword, String userPhonenumber, String userGender, String userDob, String userId) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhonenumber = userPhonenumber;
        this.userGender = userGender;
        this.userDob = userDob;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhonenumber() {
        return userPhonenumber;
    }

    public void setUserPhonenumber(String userPhonenumber) {
        this.userPhonenumber = userPhonenumber;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserDob() {
        return userDob;
    }

    public void setUserDob(String userDob) {
        this.userDob = userDob;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
