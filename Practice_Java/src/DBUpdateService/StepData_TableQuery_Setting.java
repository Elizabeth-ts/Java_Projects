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
public interface StepData_TableQuery_Setting extends DBQuery_Setting {

    public static final String TABLE_NAME = "stepdata";
    public static final String TABLE_COLUMN_NAME_STEP = "step";
    public static final String TABLE_COLUMN_NAME_ID = "id";
    public static final String TABLE_COLUMN_NAME_START = "record_start_date";
    public static final String TABLE_COLUMN_NAME_END = "record_end_date";
    public static final String TABLE_COLUMN_NAME_USERID = User_TableQuery_Setting.TABLE_COLUMN_NAME_USERID;

    public static final String QUERY_INSERT = "INSERT INTO " + DATABASE_NAME + "." + TABLE_NAME
            + "(" + TABLE_COLUMN_NAME_STEP + "," + TABLE_COLUMN_NAME_START + "," + TABLE_COLUMN_NAME_USERID
            + ") VALUES (?,?,?)";
    
    public static final String QUERY_SELECT = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME;
    public static final String QUERY_SELECT_WITH_TIME = QUERY_SELECT + " WHERE " 
            + TABLE_COLUMN_NAME_USERID + "=? AND " + TABLE_COLUMN_NAME_END + " BETWEEN ? AND ?";
    
}
