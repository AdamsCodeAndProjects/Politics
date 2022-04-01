package service;

import Entities.Comments;
import customException.CommentNotFound;
import customException.CreateCommentException;
import customException.NoValueException;
import customException.TooManyChars;
import dao.imp.CommentsDAO;

import java.util.List;

public class CommentsService implements CommentsServiceInt {

    CommentsDAO commentsDAO;
    public CommentsService(CommentsDAO commentsDAO) {
        this.commentsDAO = commentsDAO;
    }
    @Override
    public Comments createCommentService(Comments comments) {
        try {
            if(comments.getComment().length() == 0) {
                throw new NoValueException("Cannot leave boxes empty");
            } else if(comments.getComment().length() > 2000) {
                throw new TooManyChars("Your input is too long");
            } else {
                return this.commentsDAO.createComment(comments);
            }
        } catch (CreateCommentException e) {
            throw new CreateCommentException("Unable to create comment;");
        }
    }

    @Override
    public Comments getCommenetByIdService(int id) {
        try {
            return this.commentsDAO.getCommentById(id);
        }
        catch (CommentNotFound e) {
            throw new CommentNotFound("Comment not found");
        }
    }

    @Override
    public List<Comments> getAllCommentsService() {
        return this.commentsDAO.getAllComments();
    }
}
