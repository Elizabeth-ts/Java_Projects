/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Arvin
 */
public class User implements Serializable {

    private long userId;
    private String userName;
    private int userAge;
    private double userHeight, userWeight;

    public User(String userName) {
        this.userName = userName;
        userId = 0;
    }

    public User(String userName, int userAge, double userHeight, double userWeight) {
        this.userName = userName;
        this.userAge = userAge;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
    }

    public User(long userId, String userName, int userAge, double userHeight, double userWeight) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public void setUserHeight(double userHeight) {
        this.userHeight = userHeight;
    }

    public void setUserWeight(double userWeight) {
        this.userWeight = userWeight;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public double getUserHeight() {
        return userHeight;
    }

    public double getUserWeight() {
        return userWeight;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.userId ^ (this.userId >>> 32));
        hash = 67 * hash + Objects.hashCode(this.userName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", userAge=" + userAge + ", userHeight=" + userHeight + ", userWeight=" + userWeight + '}';
    }

}
