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

    private static long userIDIncrement = 0;
    private final long userId;
    private final String userName;
    private int userAge;
    private double userHeight, userWeight;

    public User(String userName, int userAge, double userHeight, double userWeight) {
        this.userName = userName;
        this.userAge = userAge;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
        userIDIncrement++;
        this.userId = userIDIncrement;
    }

    public static long getUserIDIncrement() {
        return userIDIncrement;
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

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public double getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(double userHeight) {
        this.userHeight = userHeight;
    }

    public double getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(double userWeight) {
        this.userWeight = userWeight;
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
        return "User{" + "userName=" + userName + ", userAge=" + userAge + ", userHeight=" + userHeight + ", userWeight=" + userWeight + '}';
    }

}
