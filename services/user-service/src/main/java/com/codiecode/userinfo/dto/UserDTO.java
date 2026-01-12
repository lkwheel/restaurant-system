package com.codiecode.userinfo.dto;

public class UserDTO {
    private Long userId;
    private String userName;
    private String userPassword;
    private String address;
    private String city;

    public UserDTO() {
    }

    public UserDTO(Long userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public UserDTO(Long userId, String userName, String userPassword, String city, String address) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.address = address;
        this.city = city;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
