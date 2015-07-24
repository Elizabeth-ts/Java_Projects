/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClient;

import java.io.Serializable;

/**
 *
 * @author Arvin
 */
public class MyImage implements Serializable {

    private String fileName;
    private int fileSize;
    private byte[] bytes;

    public MyImage(String fileName, int fileSize, byte[] bytes) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.bytes = bytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
