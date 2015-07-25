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
    private Stack<String> contactList;
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

    public OperationPackage(int operationCode, Stack<String> contactList) {
        this.operationCode = operationCode;
        this.contactList = contactList;
    }

    public OperationPackage(int operationCode, UserPackage userPackage, Stack<String> contactList, Stack<Post> postList) {
        this.operationCode = operationCode;
        this.userPackage = userPackage;
        this.contactList = contactList;
        this.postList = postList;
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

    public Stack<String> getContactList() {
        return contactList;
    }

    public void setContactList(Stack<String> contactList) {
        this.contactList = contactList;
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
        return "OperationPackage{" + "operationCode=" + operationCode + ", userPackage=" + userPackage + ", contactList=" + contactList + ", postList=" + postList + ", execute=" + execute + '}';
    }

}
