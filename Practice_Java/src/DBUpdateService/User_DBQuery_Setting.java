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
public interface User_DBQuery_Setting {

    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String DATABASE_NAME = "userDB";
    public static final String TABLE_NAME = "user";
    public static final String TABLE_COLUMN_NAME_USERID = "userID";
    public static final String TABLE_COLUMN_NAME_USERNAME = "name";
    public static final String TABLE_COLUMN_NAME_USERAGE = "age";
    public static final String TABLE_COLUMN_NAME_USERHEIGHT = "height";
    public static final String TABLE_COLUMN_NAME_USERWEIGHT = "weight";
    public static final int COLUMN_INDEX_USERNAME = 1;
    public static final int COLUMN_INDEX_USERAGE = 2;
    public static final int COLUMN_INDEX_USERHEIGHT = 3;
    public static final int COLUMN_INDEX_USERWEIGHT = 4;

    public static final String QUERY_CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME + ";";

    public static final String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + DATABASE_NAME + "." + TABLE_NAME
            + "(" + TABLE_COLUMN_NAME_USERID + " bigint NOT NULL AUTO_INCREMENT, " + TABLE_COLUMN_NAME_USERNAME + " varchar(255) NOT NULL, "
            + TABLE_COLUMN_NAME_USERAGE + " int, " + TABLE_COLUMN_NAME_USERHEIGHT + " double, "
            + TABLE_COLUMN_NAME_USERWEIGHT + " double, primary key(" + TABLE_COLUMN_NAME_USERID + "));";

    public static final String QUERY_INSERT = "INSERT INTO " + DATABASE_NAME + "." + TABLE_NAME + "("
            + TABLE_COLUMN_NAME_USERNAME + "," + TABLE_COLUMN_NAME_USERAGE + ","
            + TABLE_COLUMN_NAME_USERHEIGHT + "," + TABLE_COLUMN_NAME_USERWEIGHT
            + ") VALUE(?, ?, ?, ?);";

    public static final String QUERY_DROP_RECORD = "DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME + " WHERE "
            + TABLE_COLUMN_NAME_USERID + "=?;";

    public static final String QUERY_SELECT = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + ";";

    public static final String QUERY_UPDATE_NAME = "UPDATE " + DATABASE_NAME + "." + TABLE_NAME + " SET "
            + TABLE_COLUMN_NAME_USERNAME + " =? WHERE " + TABLE_COLUMN_NAME_USERID + " =?;";
    public static final String QUERY_UPDATE_AGE = "UPDATE " + DATABASE_NAME + "." + TABLE_NAME + " SET "
            + TABLE_COLUMN_NAME_USERAGE + " =? WHERE " + TABLE_COLUMN_NAME_USERID + " =?;";
    public static final String QUERY_UPDATE_HEIGHT = "UPDATE " + DATABASE_NAME + "." + TABLE_NAME + " SET "
            + TABLE_COLUMN_NAME_USERHEIGHT + " =? WHERE " + TABLE_COLUMN_NAME_USERID + " =?;";
    public static final String QUERY_UPDATE_WEIGHT = "UPDATE " + DATABASE_NAME + "." + TABLE_NAME + " SET "
            + TABLE_COLUMN_NAME_USERWEIGHT + " =? WHERE " + TABLE_COLUMN_NAME_USERID + " =?;";
}
