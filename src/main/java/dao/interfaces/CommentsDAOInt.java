package dao.interfaces;

import Entities.Comments;

import java.util.List;

public interface CommentsDAOInt {

    Comments createComment(Comments comments);

    Comments getCommentById(int id);

    List<Comments> getAllComments();

}
