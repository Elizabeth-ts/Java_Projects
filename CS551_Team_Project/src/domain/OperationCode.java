/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Arvin
 */
public interface OperationCode {

    public static final int OPERATION_ADD_USER = 0;
    public static final int OPERATION_REMOVE_USER = 1;
    public static final int OPERATION_INSERT_NEW_STEPRECORD = 2;
    public static final int OPERATION_SEARCH_STEPRECORD = 3;
    public static final int OPERATION_SEARCH_CONTACT_LIST = 4;
    public static final int OPERATION_ADD_POST = 5;
    public static final int OPERATION_GET_POST = 6;

    public static final int OPERATION_SEARCH_USER = 7;
    public static final int OPERATION_SEARCH_ADD_USER = 8;
}
