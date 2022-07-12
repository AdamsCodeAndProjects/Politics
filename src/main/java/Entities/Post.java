package Entities;

import java.util.Objects;

public class Post {

    private int postId;
    private String postText;
    private String imageFormat;
    private int rating;

    public Post(){}

    public Post(int postId, int rating, String postText, String imageFormat){
        this.postId = postId;
        this.rating = rating;
        this.postText = postText;
        this.imageFormat = imageFormat;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postText='" + postText + '\'' +
                ", imageFormat='" + imageFormat + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postId == post.postId && rating == post.rating && Objects.equals(postText, post.postText) && Objects.equals(imageFormat, post.imageFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, postText, imageFormat, rating);
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
