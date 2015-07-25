/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author Arvin
 */
public class ImageInBytes implements Serializable {

    public static final long serialVersionUID = 2L;
    private String imageFileName = null, imageFileType = null;
    private Long imageFileSize = null;
    private byte[] imageData = null;

    public ImageInBytes() {

    }

    public ImageInBytes(String imageFileName, String imageFileType, Long imageFileSize, byte[] imageData) {
        this.imageFileName = imageFileName;
        this.imageFileType = imageFileType;
        this.imageFileSize = imageFileSize;
        this.imageData = imageData;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getImageFileType() {
        return imageFileType;
    }

    public Long getImageFileSize() {
        return imageFileSize;
    }

    public byte[] getImageData() {
        return imageData;
    }

    @Override
    public String toString() {
        return "ImageInBytes{" + "imageFileName=" + imageFileName + ", imageFileType=" + imageFileType + ", imageFileSize=" + imageFileSize + '}';
    }

}
