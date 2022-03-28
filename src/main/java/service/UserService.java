package service;

import Entities.User;
import customException.*;
import dao.imp.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserServiceInt {

    private final UserDAO userDAO;

    public UserService (UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User registerUserService(User user) {
        if(user.getUsername().matches(".*\\s+.*")) {
            throw new UnallowedSpaces("No spaces allowed in username or password");
        } else if (user.getPasscode().matches(".*\\s+.*")){
            throw new UnallowedSpaces("No spaces allowed in username or password");
        } else if (user.getUsername().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty() ||
                    user.getPasscode().isEmpty() || user.getEmailAddress().isEmpty() || user.getDob().isEmpty()) {
            throw new BlankInputs("Please fill in all blanks");
        } else if (user.getUsername().length() > 20 || user.getFirstName().length() > 20 ||
                user.getLastName().length() > 20 || user.getPasscode().length() > 20 || user.getEmailAddress().length() > 50 ||
                user.getDob().length() > 10) {
            throw new TooManyChars("Too many characters");
        } else {
            return this.userDAO.createNewUser(user);
        }
    }

    @Override
    public User getUserByIdService(int userId) {
        if(userId <= 0) {
            throw new InvalidInputException("User Id must be positive");
        }
        return this.userDAO.getUserById(userId);
    }

    @Override
    public ArrayList<User> searchForUsersService(String username) {
        if (username.isEmpty()) {
            throw new InvalidInputException("Cannot leave field empty");
        } else if (username.length() > 40) {
            throw new InvalidInputException("Cannot input over 20 characters");
        }
        return this.userDAO.searchForUser(username);
    }

    @Override
    public User loginService(String username, String passcode) {
        if((username.length() > 20) || (passcode.length() > 20)) {
            throw new TooManyChars("Too many characters");
        } else if ((username.length() == 0) || (passcode.length() == 0)) {
            throw new NoValueException("You must enter your username and password");
        } else {
            return this.userDAO.login(username, passcode);
        }

    }

    @Override
    public List<User> getAllUsersService() {
        return this.userDAO.getAllUsers();
    }
}
