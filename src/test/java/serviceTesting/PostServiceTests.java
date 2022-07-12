package serviceTesting;

import Entities.Post;
import dao.imp.PostDAO;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.PostService;
import service.PostServiceInt;

import java.util.List;

public class PostServiceTests {
    static PostDAO postDAO = new PostDAO();
    static PostServiceInt postService = new PostService(postDAO);
    static Post postOne;
    static Post postTwo;
    static List<Post> newList;
    static Post tooLongPost;
    static Post tooLongPost2;
    static Post noPost;
    static Post noPostTwo;

    @BeforeClass
    private static void setup() {
        postDAO = Mockito.mock(PostDAO.class);
        postService = new PostService(postDAO);
        postOne = new Post(0, 5, "def", "5");
        postOne = new Post(1, 5, "def", "5");
        noPost = new Post(3, 5, "def", "5");
    }

    @Test
    public void getAllPostsMockito() {
        Mockito.when(postDAO.viewPosts()).thenReturn(newList);
        List<Post> result = postService.viewPostsService();
        Assert.assertEquals(result, newList);
    }

}
