package dao.imp;

import Entities.Comments;
import customException.CommentNotFound;
import dao.interfaces.CommentsDAOInt;
import utility.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentsDAO implements CommentsDAOInt {

    @Override
    public Comments createComment(Comments comments) {
        try(Connection connection = ConnectionDB.createConnection()) {
            String sql = "insert into comments values(default, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, comments.getComment());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
//            comments.setCommentId(resultSet.getInt());
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Comments getCommentById(int id) {
        try(Connection connection = ConnectionDB.createConnection()) {
            String sql = "select * from comments where commentId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                Comments comment = new Comments(
                        resultSet.getInt("commentId"),
                        resultSet.getString("comments")
                );
                return comment;
            } else {
                throw new CommentNotFound("Comment not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comments> getAllComments() {
        try(Connection connection = ConnectionDB.createConnection()) {
            String sql = "select * from comments";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Comments> comments = new ArrayList<>();
            while(resultSet.next()) {
                Comments comment = new Comments(
                        resultSet.getInt("commentId"),
                        resultSet.getString("comments")
                );
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
