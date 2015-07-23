/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalDomain;

import java.io.Serializable;
import java.util.Stack;

/**
 *
 * @author Arvin
 */
public class OperationPack implements Serializable {

    public static final long serialVersionUID = 2L;
    private UserPackage userPackage;
    private int operationCode;
    private boolean execute = false;
    private Stack<String> contactList;

    public OperationPack(int operationCode, UserPackage userPackage) {
        this.userPackage = userPackage;
        this.operationCode = operationCode;
    }

    public OperationPack(int operationCode, Stack<String> contactList) {
        this.operationCode = operationCode;
        this.contactList = contactList;
    }

    public UserPackage getUserPackage() {
        return userPackage;
    }

    public void setUserPackage(UserPackage userPackage) {
        this.userPackage = userPackage;
    }

    public int getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(int operationCode) {
        this.operationCode = operationCode;
    }

    public boolean isExecute() {
        return execute;
    }

    public void setExecute(boolean execute) {
        this.execute = execute;
    }

    public Stack<String> getContactList() {
        return contactList;
    }

    public void setContactList(Stack<String> contactList) {
        this.contactList = contactList;
    }

    @Override
    public String toString() {
        return "OperationPack{" + "userPackage=" + userPackage + ", operationCode=" + operationCode + ", execute=" + execute + ", contactList=" + contactList + '}';
    }

}
