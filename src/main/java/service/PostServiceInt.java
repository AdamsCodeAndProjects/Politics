package service;

import Entities.Post;

import java.util.List;

public interface PostServiceInt {
    Post createPostService(Post post);

    List<Post> viewPostsService();
}
