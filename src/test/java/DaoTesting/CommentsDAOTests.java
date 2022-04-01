package DaoTesting;

import Entities.Comments;
import dao.imp.CommentsDAO;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CommentsDAOTests {

    CommentsDAO commentsDAO = new CommentsDAO();

    @Test
    void testCreateComment() {
        Comments newComment = new Comments(0, "This article is no good");
        Comments returnedComment = commentsDAO.createComment(newComment);
        Assert.assertTrue(returnedComment.getCommentId() != 0);
    }

    @Test
    void testSelectCommentById() {
        Comments comments = commentsDAO.getCommentById(1);
        Assert.assertEquals(comments.getCommentId(), 1);
    }

    @Test
    void testGetAllComments() {
        List<Comments> comments = commentsDAO.getAllComments();
        Assert.assertTrue(comments.size() >= 2);
    }

    @Test
    void testGetAllCommentsTwo() {
        List<Comments> comments = commentsDAO.getAllComments();
        Assert.assertTrue(comments.size() < 200);
    }
}
