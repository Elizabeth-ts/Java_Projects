/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angel_cs532_midterm_Q2_test.server;

/**
 *
 * @author Arvin
 */
public interface MySQL_DB_Query {

    public static final String DATABASE_NAME = "studentDB";
    public static final String TABLE_NAME = "scores";
    public static final String TABLE_COLUMN_NAME = "name";
    public static final String TABLE_COLUMN_SCORE = "score";
    public static final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS "
            + DATABASE_NAME + ";";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + DATABASE_NAME + "." + TABLE_NAME
            + "(" + TABLE_COLUMN_NAME + " varchar(255), " + TABLE_COLUMN_SCORE + " double, primary key ("
            + TABLE_COLUMN_NAME + "));";
}
