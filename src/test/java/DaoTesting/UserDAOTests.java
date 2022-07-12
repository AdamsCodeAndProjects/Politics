package DaoTesting;

import Entities.User;
import customException.DuplicateUsername;
import customException.UserNotFound;
import dao.imp.UserDAO;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDAOTests {
    UserDAO userDAO = new UserDAO();

    @Test
    void testRegisterUser() {
        String date = "03242022";
        User newUser = new User(0, "Tim", "Sanders", "123@acs.com", "Test", "yoyoyo", "Im boring", "01021944","img", 100);
        User newlyCreatedUser = userDAO.createNewUser(newUser);
        Assert.assertEquals(newlyCreatedUser.getFirstName(), "Tim");
    }

    // ------------------------------------------- Sad path testing -------------------------------------------
    @Test
    void testDuplicateUsername() {
        try {
            User newUser = new User(1, "Rick", "Sanders", "234@abc.com", "myUN", "nocando", "I like things","01012000", "imgsrc", 100);
            userDAO.createNewUser(newUser);
        } catch (DuplicateUsername e) {
            Assert.assertEquals("This username is already taken", e.getMessage());
        }
    }

    // ------------------------------------------- Testing login -------------------------------------------
    @Test
    void testLoginSuccess() {
        User user = userDAO.login("username", "passcode");
        Assert.assertEquals(user.getUsername(), "test");
    }

    @Test
    void testLoginTwo() {
        User user = userDAO.login("test", "tester");
        Assert.assertEquals(user.getUsername(), "test");
    }

    //  ------------------------------------------- Search Tests -------------------------------------------
    @Test
    void testSearchUserSuccess() {
        ArrayList<User> users = userDAO.searchForUser("username");
        for (User user : users) {
            if (!user.getUsername().contains("username")) {
                Assert.fail();
            }
        }
        Assert.assertTrue(true);
    }

    @Test
    void testGetUserByIdSuccess() {
        User user = userDAO.getUserById(10);
        Assert.assertEquals(user.getUserId(), 10);
    }

    @Test
    void testGetUserByIdSuccessTwo() {
        User user = userDAO.getUserById(2);
        Assert.assertEquals(user.getUserId(), 2);
    }

    // ------------------------------------------- Get All Users -------------------------------------------
    @Test
    void testGetAllUsers() {
        List<User> users = userDAO.getAllUsers();
        for (User u : users){
            Assert.assertTrue(users.size() >= 2);
        }
    }

    @Test
    void testGetAllUsersTwo() {
        List<User> users = userDAO.getAllUsers();
        for (User u : users){
            Assert.assertTrue(users.size() <= 200);
        }
    }

    //  ----------------------------------  Sad path -------------------------------------------

    @Test
    void testSearchByUsernameBad() {
        ArrayList<User> users = userDAO.searchForUser("notAUsername");
        Assert.assertTrue(users.isEmpty());
    }

    @Test(expectedExceptions = UserNotFound.class, expectedExceptionsMessageRegExp = "User Not Found")
    void testRequestLoginFailWrongPW() {
        userDAO.login("test", "77443");
    }

    @Test(expectedExceptions = UserNotFound.class, expectedExceptionsMessageRegExp = "User Not Found")
    void testRequestLoginFailWrongUN() {
        userDAO.login("testiclses", "passcode");
    }

    @Test(expectedExceptions = UserNotFound.class, expectedExceptionsMessageRegExp = "User Not Found")
    void testRequestLoginFailWrongBoth() {
        userDAO.login("343232432", "7744332");
    }




}
