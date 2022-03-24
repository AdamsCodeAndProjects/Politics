package dao.interfaces;

import Entities.User;

import java.util.ArrayList;
import java.util.List;

public interface UserDAOInt {

    User createNewUser(User user);

    User login(String username, String passcode);

    User getUserById(int userId);

    ArrayList<User> searchForUser(String username);

    List<User> getAllUsers();
}
