package Entities;

import java.util.Objects;

public class Comments {

    private int commentId;
    private String comment;

    public Comments(){}

    public Comments(int commentId, String comment) {
        this.commentId = commentId;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "commentId=" + commentId +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return commentId == comments.commentId && comment.equals(comments.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, comment);
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
