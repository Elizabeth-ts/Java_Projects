/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalDomain;

import java.io.Serializable;

/**
 *
 * @author Arvin
 */
public class UserPackage implements Serializable {

    public static final long serialVersionUID = 2L;
    private long userId;
    private String userName, phoneNumber;
    private Post post;
    private StepRecord stepRecord;

    public UserPackage() {
    }

    public UserPackage(String userName, String phoneNumber) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public UserPackage(long userId, Post post) {
        this.userId = userId;
        this.post = post;
    }

    public UserPackage(String phoneNumber, Post post) {
        this.phoneNumber = phoneNumber;
        this.post = post;
    }

    public UserPackage(long userId, StepRecord stepRecord) {
        this.userId = userId;
        this.stepRecord = stepRecord;
    }

    public UserPackage(String phoneNumber, StepRecord stepRecord) {
        this.phoneNumber = phoneNumber;
        this.stepRecord = stepRecord;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public StepRecord getStepRecord() {
        return stepRecord;
    }

    public void setStepRecord(StepRecord stepRecord) {
        this.stepRecord = stepRecord;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.userId ^ (this.userId >>> 32));
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
        return this.userId == other.userId;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", phoneNumber=" + phoneNumber + ", post=" + post + ", stepRecord=" + stepRecord + '}';
    }

}
