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
public interface TableQuery_Post extends DataBaseQuery {

    public static final String TABLE_NAME = "post";
    public static final String TABLE_COLUMN_NAME_POSTID = "post_id";
    public static final String TABLE_COLUMN_NAME_PHONE_NUMBER = "phone_number";
    public static final String TABLE_COLUMN_NAME_MESSAGE = "message";
    public static final String TABLE_COLUMN_NAME_IMAGE_URL = "image_file_url";
    public static final String TABLE_COLUMN_NAME_IMAGE_FILE_NAME = "image_file_name";
    public static final String TABLE_COLUMN_NAME_IMAGE_FILE_TYPE = "image_file_type";
    public static final String TABLE_COLUMN_NAME_IMAGE_FILE_SIZE = "image_file_size";

    public static final String INSERT = "INSERT INTO " + DATABASE_NAME + "." + TABLE_NAME + "("
            + TABLE_COLUMN_NAME_PHONE_NUMBER + "," + TABLE_COLUMN_NAME_MESSAGE + ","
            + TABLE_COLUMN_NAME_IMAGE_URL + "," + TABLE_COLUMN_NAME_IMAGE_FILE_NAME + ","
            + TABLE_COLUMN_NAME_IMAGE_FILE_TYPE + "," + TABLE_COLUMN_NAME_IMAGE_FILE_SIZE
            + ")VALUE(?,?,?,?,?,?)";

    public static final String SELECT = "SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME
            + " WHERE " + TABLE_COLUMN_NAME_PHONE_NUMBER + "=?";

}
