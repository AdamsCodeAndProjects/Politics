package dao.interfaces;

import Entities.Post;

import java.util.List;

public interface PostDAOInt {

    Post createPost(Post post);

    List<Post> viewPosts();
}
