/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase_Updata_Service;

import SQL_PreparedStatements.*;
import domain.ImageInBytes;
import domain.OperationCode;
import domain.OperationPackage;
import domain.Post;
import domain.StepRecord;
import domain.UserPackage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Arvin
 */
public class UserServiceInterfaceImpl extends UnicastRemoteObject
        implements UserServiceInterface, TableQuery_Post, TableQuery_StepRecord, TableQuery_User {

    private Connection jdbcConnection;
    private Statement jdbcStatement;
    private ResultSet jdbcResultSet;
    private PreparedStatement preparedStat;
    private String postImageFilePath ="C:\\imageTest";

    public UserServiceInterfaceImpl() throws RemoteException {
        try {
            // Get connection from local database
            jdbcConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            jdbcConnection.setAutoCommit(false);
            // Create statement
            jdbcStatement = jdbcConnection.createStatement();
            jdbcConnection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public OperationPackage addUser(OperationPackage operation) throws RemoteException {
        trace("-------------------db connect");
        String imageURL = postImageFilePath + File.separator + operation.getUserPackage().getPhoneNumber() + File.separator
                + operation.getUserPackage().getImageInBytes().getImageFileName();
        trace("img URL "+imageURL);
        InputStream is = null;
        File newImageFile = null;
        if (operation.getUserPackage().getImageInBytes().getImageData() != null) {
            try {
                is = new ByteArrayInputStream(operation.getUserPackage().getImageInBytes().getImageData());
                BufferedImage bImage = ImageIO.read(is);
                newImageFile = new File(imageURL);
                newImageFile.getParentFile().mkdirs();
                newImageFile.createNewFile();
                ImageIO.write(bImage, operation.getUserPackage().getImageInBytes().getImageFileType(), newImageFile);
            } catch (IOException ex) {
                Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            preparedStat = jdbcConnection.prepareStatement(TableQuery_User.INSERT);
            preparedStat.setString(1, operation.getUserPackage().getUserName());
            preparedStat.setString(2, operation.getUserPackage().getPhoneNumber());
            preparedStat.setString(3, imageURL);
            preparedStat.setString(4, operation.getUserPackage().getImageInBytes().getImageFileName());
            preparedStat.setString(5, operation.getUserPackage().getImageInBytes().getImageFileType());
            preparedStat.setLong(6, operation.getUserPackage().getImageInBytes().getImageFileSize());
            
            preparedStat.executeUpdate();
            jdbcConnection.commit();
            jdbcResultSet = jdbcStatement.executeQuery("select last_insert_id() as last_id");
            if (jdbcResultSet.next()) {
                operation.getUserPackage().setUserId(jdbcResultSet.getInt("last_id"));
            }
            operation.setExecute(true);
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return operation;
        }
    }
    @Override
    public OperationPackage searchAddUser(OperationPackage operation) throws RemoteException {
        int totalStepCount = 0;
        try {
            preparedStat = jdbcConnection.prepareStatement(TableQuery_User.GET_USER_FROM_PHONE_NUMBER);
            preparedStat.setString(1, operation.getUserPackage().getPhoneNumber());
            
            jdbcResultSet = preparedStat.executeQuery();
            if (jdbcResultSet != null) {
                trace("searchAddUser: search result not null");
                while (jdbcResultSet.next()) {
                    BufferedImage img = null;
                    ByteArrayOutputStream baos = null;
                    byte[] imageData = null;
                    String fileName = jdbcResultSet.getString(TableQuery_User.TABLE_COLUMN_NAME_IMAGE_FILE_NAME);
                    String fileURL = jdbcResultSet.getString(TableQuery_User.TABLE_COLUMN_NAME_IMAGE_FILE_URL);
                    String fileType = jdbcResultSet.getString(TableQuery_User.TABLE_COLUMN_NAME_IMAGE_FILE_TYPE);
                    Long fileSize = jdbcResultSet.getLong(TableQuery_User.TABLE_COLUMN_NAME_IMAGE_FILE_SIZE);
                     
                    img = ImageIO.read(new File(fileURL));
                    baos = new ByteArrayOutputStream();

                    ImageIO.write(img, fileType, baos);
                    baos.flush();

                    imageData = baos.toByteArray();
                    baos.close();

                    ImageInBytes profileImage = new ImageInBytes(fileName, fileType, fileSize, imageData);
                    
                    UserPackage user= new UserPackage((long)jdbcResultSet.getInt(TableQuery_User.TABLE_COLUMN_NAME_USERID),
                                                        jdbcResultSet.getString(TableQuery_User.TABLE_COLUMN_NAME_USERNAME),
                                                        jdbcResultSet.getString(TableQuery_User.TABLE_COLUMN_NAME_PHONE_NUMBER),
                                                        profileImage
                                                         );
                    operation=null;
                    operation=new OperationPackage(OperationCode.OPERATION_SEARCH_USER, user, null, null);
                }
            }if(operation.getUserPackage().getUserId()==0){
                trace("searchAddUser: search result is null");
                operation=addUser(operation);
            }
            //trace("step after execution:" + totalStepCount);
            //operation.getUserPackage().getStepRecord().setStepCount(totalStepCount);
            operation.setExecute(true);
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return operation;
        }
    }

    @Override
    public OperationPackage searchUser(OperationPackage operation) throws RemoteException {
        int totalStepCount = 0;
        try {
            preparedStat = jdbcConnection.prepareStatement(TableQuery_User.GET_USER_FROM_PHONE_NUMBER);
            preparedStat.setString(1, operation.getUserPackage().getPhoneNumber());
            jdbcResultSet = preparedStat.executeQuery();
            if (jdbcResultSet != null) {
                while (jdbcResultSet.next()) {
                    BufferedImage img = null;
                    ByteArrayOutputStream baos = null;
                    byte[] imageData = null;
                    String fileName = jdbcResultSet.getString(TableQuery_User.TABLE_COLUMN_NAME_IMAGE_FILE_NAME);
                    String fileURL = jdbcResultSet.getString(TableQuery_User.TABLE_COLUMN_NAME_IMAGE_FILE_URL);
                    String fileType = jdbcResultSet.getString(TableQuery_User.TABLE_COLUMN_NAME_IMAGE_FILE_TYPE);
                    Long fileSize = jdbcResultSet.getLong(TableQuery_User.TABLE_COLUMN_NAME_IMAGE_FILE_SIZE);
                     
                    img = ImageIO.read(new File(fileURL));
                    baos = new ByteArrayOutputStream();

                    ImageIO.write(img, fileType, baos);
                    baos.flush();

                    imageData = baos.toByteArray();
                    baos.close();

                    ImageInBytes profileImage = new ImageInBytes(fileName, fileType, fileSize, imageData);
                    
                    UserPackage user= new UserPackage((long)jdbcResultSet.getInt(TableQuery_User.TABLE_COLUMN_NAME_USERID),
                                                        jdbcResultSet.getString(TableQuery_User.TABLE_COLUMN_NAME_USERNAME),
                                                        jdbcResultSet.getString(TableQuery_User.TABLE_COLUMN_NAME_PHONE_NUMBER),
                                                        profileImage);
                    operation=null;
                    operation=new OperationPackage(OperationCode.OPERATION_SEARCH_USER, user, null, null);
                }
            }
            //trace("step after execution:" + totalStepCount);
            //operation.getUserPackage().getStepRecord().setStepCount(totalStepCount);
            operation.setExecute(true);
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return operation;
        }
    }
    @Override
    public OperationPackage removeUser(OperationPackage operation) throws RemoteException {
        try {
            preparedStat = jdbcConnection.prepareStatement(TableQuery_User.DELETE);
            preparedStat.setString(1, operation.getUserPackage().getPhoneNumber());
            preparedStat.execute();
            jdbcConnection.commit();
            operation.setExecute(true);
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return operation;
        }

    }

    @Override
    public OperationPackage insertNewRecord(OperationPackage operation) throws RemoteException {
        StepRecord data = operation.getUserPackage().getStepRecord();
        try {
            preparedStat = jdbcConnection.prepareStatement(TableQuery_StepRecord.INSERT);
            preparedStat.setInt(1, data.getStepCount());
            preparedStat.setTimestamp(2, data.getRecordStartDate());
            preparedStat.setTimestamp(3, data.getRecordEndDate());
            preparedStat.setString(4, operation.getUserPackage().getPhoneNumber());
            preparedStat.executeUpdate();
            jdbcConnection.commit();
            jdbcResultSet = jdbcStatement.executeQuery("select last_insert_id() as last_id");
            if (jdbcResultSet.next()) {
                operation.getUserPackage().getStepRecord().setStepRecordId(jdbcResultSet.getInt("last_id"));
            }
            operation.setExecute(true);
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return operation;
    }

    @Override
    public OperationPackage searchStepTotalForUserWithinPeriod(OperationPackage operation) throws RemoteException {
        int totalStepCount = 0;
        try {
            preparedStat = jdbcConnection.prepareStatement(TableQuery_StepRecord.SELECT);
            preparedStat.setString(1, operation.getUserPackage().getPhoneNumber());
            preparedStat.setTimestamp(2, operation.getUserPackage().getStepRecord().getRecordStartDate());
            preparedStat.setTimestamp(3, operation.getUserPackage().getStepRecord().getRecordEndDate());
            jdbcResultSet = preparedStat.executeQuery();
            if (jdbcResultSet != null) {
                while (jdbcResultSet.next()) {
                    totalStepCount += jdbcResultSet.getInt(TABLE_COLUMN_NAME_STEP);
                }
            }
            trace("step after execution:" + totalStepCount);
            operation.getUserPackage().getStepRecord().setStepCount(totalStepCount);
            operation.setExecute(true);
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return operation;
        }
    }

    @Override
    public OperationPackage searchContactList(OperationPackage operation) throws RemoteException {
        final Stack<String> confirmList = new Stack<>();
        System.out.println(operation.getContactList());
        try {
            preparedStat = jdbcConnection.prepareStatement("select phone_number from cs551_team_project.user");
            jdbcResultSet = preparedStat.executeQuery();
            if (jdbcResultSet != null) {
                while (jdbcResultSet.next()) {
                    String phoneNumber = jdbcResultSet.getString(TableQuery_User.TABLE_COLUMN_NAME_PHONE_NUMBER);
                    if (operation.getContactList().contains(phoneNumber)) {
                        confirmList.push(phoneNumber);
                    }
                }
            }
            operation.setContactList(confirmList);
            operation.setExecute(true);
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println(confirmList);
            return operation;
        }
    }

    @Override
    public OperationPackage addPost(OperationPackage operation) throws RemoteException {
        trace("hello World");
        if (operation.getUserPackage().getPost() == null) {
            trace("null post");
            return operation;
        }
        trace("---");
        
        String imageURL = postImageFilePath + File.separator + operation.getUserPackage().getPhoneNumber() + File.separator
                + operation.getUserPackage().getPost().getImageInBytes().getImageFileName();
        trace(imageURL+"");
        
        InputStream is = null;
        File newImageFile = null;
        if (operation.getUserPackage().getPost().getImageInBytes().getImageData() != null) {
            try {
                is = new ByteArrayInputStream(operation.getUserPackage().getPost().getImageInBytes().getImageData());
                BufferedImage bImage = ImageIO.read(is);
                newImageFile = new File(imageURL);
                newImageFile.getParentFile().mkdirs();
                newImageFile.createNewFile();
                ImageIO.write(bImage, operation.getUserPackage().getPost().getImageInBytes().getImageFileType(), newImageFile);
            } catch (IOException ex) {
                Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            trace("Add Post b4 prep stat");
            preparedStat = jdbcConnection.prepareStatement(TableQuery_Post.INSERT);
          
            preparedStat.setString(1, operation.getUserPackage().getPhoneNumber());
            preparedStat.setString(2, operation.getUserPackage().getPost().getMessage());
            preparedStat.setString(3, imageURL);
            preparedStat.setString(4, operation.getUserPackage().getPost().getImageInBytes().getImageFileName());
            preparedStat.setString(5, operation.getUserPackage().getPost().getImageInBytes().getImageFileType());
            preparedStat.setLong(6, operation.getUserPackage().getPost().getImageInBytes().getImageFileSize());
            trace(preparedStat.toString());
            preparedStat.executeUpdate();        
            jdbcConnection.commit();
            jdbcResultSet = jdbcStatement.executeQuery("select last_insert_id() as last_id");
            if (jdbcResultSet.next()) {
                operation.getUserPackage().getPost().setPostId(jdbcResultSet.getInt("last_id"));
            }
            operation.setExecute(true);
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return operation;
        }
    }

    @Override
    public OperationPackage getPost(OperationPackage operation) throws RemoteException {
        Stack<Post> postStack = new Stack<>();
        int postCount = 7;
        try {
            trace(operation.getUserPackage().getPhoneNumber());
            preparedStat = jdbcConnection.prepareStatement(TableQuery_Post.SELECT);
            preparedStat.setString(1, operation.getUserPackage().getPhoneNumber());
            preparedStat.setInt(2, postCount);
            jdbcResultSet = preparedStat.executeQuery();
            if (jdbcResultSet != null) {
                while (jdbcResultSet.next()) {
                    BufferedImage img = null;
                    ByteArrayOutputStream baos = null;
                    byte[] imageData = null;
                    long postId = jdbcResultSet.getLong(TableQuery_Post.TABLE_COLUMN_NAME_POSTID);
                    String fileName = jdbcResultSet.getString(TableQuery_Post.TABLE_COLUMN_NAME_IMAGE_FILE_NAME);
                    String fileURL = jdbcResultSet.getString(TableQuery_Post.TABLE_COLUMN_NAME_IMAGE_FILE_URL);
                    String fileType = jdbcResultSet.getString(TableQuery_Post.TABLE_COLUMN_NAME_IMAGE_FILE_TYPE);
                    String postMessage = jdbcResultSet.getString(TableQuery_Post.TABLE_COLUMN_NAME_MESSAGE);
                    String postTime = jdbcResultSet.getString(TableQuery_Post.TABLE_COLUMN_NAME_PostTime);
                    //String phoneNumber = jdbcResultSet.getString(TableQuery_Post.TABLE_COLUMN_NAME_PHONE_NUMBER);
                    Long fileSize = jdbcResultSet.getLong(TableQuery_Post.TABLE_COLUMN_NAME_IMAGE_FILE_SIZE);

                    img = ImageIO.read(new File(fileURL));
                    baos = new ByteArrayOutputStream();

                    ImageIO.write(img, fileType, baos);
                    baos.flush();

                    imageData = baos.toByteArray();
                    baos.close();

                    ImageInBytes postImage = new ImageInBytes(fileName, fileType, fileSize, imageData);
                    Post newPost = new Post(postId, postMessage, postImage, postTime);
                    postStack.push(newPost);
                }
            }
            operation.setPostList(postStack);
            operation.setExecute(true);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return operation;
        }
    }

    private void trace(String s) {
        System.out.println(s);
    }
}
