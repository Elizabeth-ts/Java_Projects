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
public class UserPackage implements Serializable {

    public static final long serialVersionUID = 2L;
    private long userId;
    private String userName, phoneNumber;
    private StepRecord stepRecord;
    private Post post;

    public UserPackage(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserPackage(String userName, String phoneNumber) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public UserPackage(String phoneNumber, StepRecord stepRecord) {
        this.phoneNumber = phoneNumber;
        this.stepRecord = stepRecord;
    }

    public UserPackage(String phoneNumber, Post post) {
        this.phoneNumber = phoneNumber;
        this.post = post;
    }

    public UserPackage(long userId, String userName, String phoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public UserPackage(String userName, String phoneNumber, StepRecord stepRecord, Post post) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.stepRecord = stepRecord;
        this.post = post;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public StepRecord getStepRecord() {
        return stepRecord;
    }

    public void setStepRecord(StepRecord stepRecord) {
        this.stepRecord = stepRecord;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + (int) (this.userId ^ (this.userId >>> 32));
        hash = 43 * hash + Objects.hashCode(this.phoneNumber);
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
        final UserPackage other = (UserPackage) obj;
        if (this.userId != other.userId) {
            return false;
        }
        return this.phoneNumber.equals(other.phoneNumber);
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", phoneNumber=" + phoneNumber + ", stepRecord=" + stepRecord + ", post=" + post + '}';
    }

}
