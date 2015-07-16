/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBUpdateService;

/**
 *
 * @author Arvin
 */
public interface DBQuery_Setting {

    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String DATABASE_NAME = "userDB";
    public static final String QUERY_CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME + ";";

}
