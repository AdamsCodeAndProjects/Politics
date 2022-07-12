package DaoTesting;

import Entities.Post;
import dao.imp.PostDAO;
import dao.interfaces.PostDAOInt;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PostTests {

    PostDAOInt postDAO = new PostDAO();

    @Test
    void createPost() {
        Post post = new Post(0, 25, "Ok", "");
        Post returnedPost = postDAO.createPost(post);
        Assert.assertTrue(returnedPost.getPostId() != 0);
    }

    @Test
    void viewPosts() {
        List<Post> posts = postDAO.viewPosts();
        Assert.assertTrue(posts.size() >= 1);
    }
}
