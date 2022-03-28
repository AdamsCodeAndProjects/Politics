package service;

import Entities.User;

import java.util.ArrayList;
import java.util.List;

public interface UserServiceInt {

    User registerUserService(User user);

    User getUserByIdService(int userId);

    ArrayList<User> searchForUsersService(String username);

    User loginService(String username, String passcode);

    List<User> getAllUsersService();
}
