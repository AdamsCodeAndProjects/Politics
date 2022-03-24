package dao.imp;

import Entities.User;
import customException.DuplicateEmail;
import customException.DuplicateUsername;
import customException.UserNotFound;
import customException.UsernameOrPasscodeException;
import dao.interfaces.UserDAOInt;
import utility.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDAOInt {


    @Override
    public User createNewUser(User user) {
        try(Connection connection = ConnectionDB.createConnection()) {
            String sql = "insert into user_table values(default, ?, ?, ?, ? ,?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmailAddress());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPasscode());
            preparedStatement.setString(6, user.getUserAboutMe());
            preparedStatement.setString(7, user.getDob());
            preparedStatement.setString(8, user.getImageFormat());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            user.setUserId(rs.getInt("user_id"));
            return new User(
                    rs.getInt("user_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("dob")
            );
    } catch (SQLException e){
        if(e.getMessage().contains("username")) {
            throw new DuplicateUsername("This username is already taken");
        } else if (e.getMessage().contains("email")){
            throw new DuplicateEmail("Email is already in use");
        } else {
            e.printStackTrace();
            return null;
            }
        }
    }

    @Override
    public User login(String username, String passcode) {
        try(Connection connection = ConnectionDB.createConnection()) {
            String sql = "select user_id, first_name, last_name, email, username, user_dob" +
                    " from user_table" +
                    " where username = ? and passcode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passcode);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("dob")
                );
            } else {
                throw new UsernameOrPasscodeException("User Not Found");
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public User getUserById(int userId) {
        try (Connection connection = ConnectionDB.createConnection()) {
            String sql = "select user_id, first_name, last_name, email, username, dob" +
                    " from user_table" +
                    " where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_Id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("dob")
                );
            } else {
                throw new UserNotFound("User Not Found");
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public ArrayList<User> searchForUser(String username) {
        try(Connection connection = ConnectionDB.createConnection()) {
            String sql = "select * from user_table where username is like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + username + "%");
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(
                        new User(
                                rs.getInt("user_Id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email"),
                                rs.getString("username"),
                                rs.getString("dob")
                        )
                );
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection connection = ConnectionDB.createConnection()) {
            String sql = "select * from user_table";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_Id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("passcode"),
                        rs.getString("user_about_me"),
                        rs.getString("dob"),
                        rs.getString("image_format")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
