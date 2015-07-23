/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalDomain;

import java.io.Serializable;

/**
 *
 * @author Arvin
 */
public class Post implements Serializable {

    public static final long serialVersionUID = 2L;
    private String message;
    private boolean hasImage;
    private long postId;

    public Post(String message, boolean hasImage) {
        this.message = message;
        this.hasImage = hasImage;
    }

    public Post(String message, boolean hasImage, long postId) {
        this.message = message;
        this.hasImage = hasImage;
        this.postId = postId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Post{" + "message=" + message + ", hasImage=" + hasImage + ", postId=" + postId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.postId ^ (this.postId >>> 32));
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
        return this.postId == other.postId;
    }

}
