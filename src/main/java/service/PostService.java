package service;

import Entities.Post;
import customException.OutOfRangeException;
import dao.imp.PostDAO;

import java.util.List;

public class PostService implements PostServiceInt{

    PostDAO postDAO;

    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }


    //  Possibly change.  Shouldnt need to rate when creating a post
    //  New method may be needed :  Rate post
    @Override
    public Post createPostService(Post post) {
        if((post.getRating() > 100) || (post.getRating() < 0)) {
            throw new OutOfRangeException("Your rating is out of range");
        } else {
            return post;
        }
    }

    @Override
    public List<Post> viewPostsService() {
        return this.postDAO.viewPosts();
    }
}
