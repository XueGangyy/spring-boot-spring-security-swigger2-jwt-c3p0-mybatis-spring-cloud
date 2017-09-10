package com.ynu.yuyue.cloud.bookservice.domain;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by yuyue on 2017/9/7.
 */
public class User {
    @NotNull(message = "user.userEmailNotNull")
    private String userEmail;

    private String userName;
    private String userPassword;
    private String userTelephone;
    private String userAddress;
    private double userGood;//好评程度
    private double userTrust;//信任等级
    private String role;
    private Date lastPasswordResetDate;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public double getUserGood() {
        return userGood;
    }

    public void setUserGood(double userGood) {
        this.userGood = userGood;
    }

    public double getUserTrust() {
        return userTrust;
    }

    public void setUserTrust(double userTrust) {
        this.userTrust = userTrust;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userTelephone='" + userTelephone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userGood=" + userGood +
                ", userTrust=" + userTrust +
                ", role='" + role + '\'' +
                '}';
    }
}
