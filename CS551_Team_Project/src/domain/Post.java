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
public class Post implements Serializable {

    public static final long serialVersionUID = 2L;
    private long postId;
    private String message = null;
    private ImageInBytes imageInBytes = new ImageInBytes();

    public Post(String message) {
        this.message = message;
    }

    public Post(ImageInBytes image) {
        this.imageInBytes = image;
    }

    public Post(String message, ImageInBytes imageInBytes) {
        this.message = message;
        this.imageInBytes = imageInBytes;
    }

    public Post(long postId, String message, ImageInBytes imageInBytes) {
        this.postId = postId;
        this.message = message;
        this.imageInBytes = imageInBytes;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ImageInBytes getImageInBytes() {
        return imageInBytes;
    }

    public void setImageInBytes(ImageInBytes imageInBytes) {
        this.imageInBytes = imageInBytes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.postId ^ (this.postId >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (this.postId != other.postId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", message=" + message + ", imageInBytes=" + imageInBytes + '}';
    }

}
