package dao.imp;

import Entities.Post;
import dao.interfaces.PostDAOInt;
import utility.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO implements PostDAOInt {

    @Override
    public Post createPost(Post post) {
        try (Connection connection = ConnectionDB.createConnection()) {
            String sql = "insert into post values(default, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, post.getPostText());
            preparedStatement.setString(2, post.getImageFormat());
            preparedStatement.setInt(3, post.getRating());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            post.setPostId(resultSet.getInt("postId"));
            return post;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Post> viewPosts() {
        try (Connection connection = ConnectionDB.createConnection()) {
            String sql = "select * from post";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Post> posts = new ArrayList<>();
            while(resultSet.next()) {
                Post post = new Post(
                        resultSet.getInt("postId"),
                        resultSet.getInt("rating"),
                        resultSet.getString("postText"),
                        resultSet.getString("imageFormat")
                );
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Consider creating ratePost() function

}
