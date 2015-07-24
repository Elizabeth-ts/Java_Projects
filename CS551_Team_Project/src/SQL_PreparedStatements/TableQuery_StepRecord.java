/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL_PreparedStatements;

/**
 *
 * @author Arvin
 */
public interface TableQuery_StepRecord extends DataBaseQuery {

    public static final String TABLE_NAME = "step_record";
    public static final String TABLE_COLUMN_NAME_STEP = "step";
    public static final String TABLE_COLUMN_NAME_STEP_RECORD_ID = "step_record_id";
    public static final String TABLE_COLUMN_NAME_START_DATE = "record_start_date";
    public static final String TABLE_COLUMN_NAME_END_DATE = "record_end_date";
    public static final String TABLE_COLUMN_NAME_PHONE_NUMBER = "phone_number";

    public static final String INSERT = "INSERT INTO " + DATABASE_NAME + "." + TABLE_NAME
            + "(" + TABLE_COLUMN_NAME_STEP + "," + TABLE_COLUMN_NAME_START_DATE + ","
            + TABLE_COLUMN_NAME_END_DATE + "," + TABLE_COLUMN_NAME_PHONE_NUMBER
            + ") VALUES (?,?,?,?)";

    public static final String SELECT = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME
            + " WHERE " + TABLE_COLUMN_NAME_PHONE_NUMBER + " =? AND "
            + TABLE_COLUMN_NAME_END_DATE + " BETWEEN ? AND ?";

}
