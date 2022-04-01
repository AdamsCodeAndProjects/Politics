package service;

import Entities.Comments;

import java.util.List;

public interface CommentsServiceInt {
    Comments createCommentService(Comments comments);

    Comments getCommenetByIdService(int id);

    List<Comments> getAllCommentsService();
}
