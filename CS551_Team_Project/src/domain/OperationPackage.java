/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Stack;

/**
 *
 * @author Arvin
 */
public class OperationPackage implements Serializable {

    public static final long serialVersionUID = 2L;
    private int operationCode;
    private UserPackage userPackage;
    private Stack<String> phoneNumberList;
    private Stack<Post> postList;
    private boolean execute = false;

    public OperationPackage(int operationCode) {
        this.operationCode = operationCode;
    }

    public OperationPackage(Stack<Post> postList) {
        this.postList = postList;
    }

    public OperationPackage(int operationCode, UserPackage userPackage) {
        this.operationCode = operationCode;
        this.userPackage = userPackage;
    }

    public OperationPackage(int operationCode, Stack<String> phoneNumberList) {
        this.operationCode = operationCode;
        this.phoneNumberList = phoneNumberList;
    }

    public int getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(int operationCode) {
        this.operationCode = operationCode;
    }

    public UserPackage getUserPackage() {
        return userPackage;
    }

    public void setUserPackage(UserPackage userPackage) {
        this.userPackage = userPackage;
    }

    public Stack<String> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(Stack<String> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }

    public Stack<Post> getPostList() {
        return postList;
    }

    public void setPostList(Stack<Post> postList) {
        this.postList = postList;
    }

    public boolean isExecute() {
        return execute;
    }

    public void setExecute(boolean execute) {
        this.execute = execute;
    }

    @Override
    public String toString() {
        return "OperationPackage{" + "operationCode=" + operationCode + ", userPackage=" + userPackage + ", phoneNumberList=" + phoneNumberList + ", postList=" + postList + ", execute=" + execute + '}';
    }

}
