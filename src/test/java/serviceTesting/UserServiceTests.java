package serviceTesting;

import Entities.User;
import customException.*;
import dao.imp.UserDAO;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.UserService;
import service.UserServiceInt;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTests {

    public static UserDAO userDAO = new UserDAO();
    public static UserServiceInt userService = new UserService(userDAO);

    //  Create test for politicalView being out of range : 120 ||  -120

    static User userProfile;
    static User mockUserOne;
    static User mockUserTwo;
    static User duplicateEmailUser;
    static User userProfile2;
    static User returnedProfile;
    static User badUsername;
    static User duplicateUsername;
    static User usernameSpaces;
    static User passwordSpaces;
    static List<User> newList;
    static ArrayList<User> anotherList = new ArrayList<>();
    static User badPasscode;
    static User otherProfile;
    static User blankSpaces;

    @BeforeClass
    public void setup() {
        userDAO = Mockito.mock(UserDAO.class);
        userService = new UserService(userDAO);
        mockUserOne = new User(0, "mocky", "mockinly", "mock@123.com", "moo", "pass", "hiya", "01011988", "img", 50);
        mockUserTwo = new User(1, "mocky", "mocker", "mocke@123.com", "riri", "pass", "hiya", "01021988", "img", 50);
        duplicateEmailUser = new User(0, "ben", "turkey", "sick@123.com", "moo", "passy", "hiya", "02011988", "img", 50);
        userProfile = new User(0, "Razor", "Ramon", "iwrestleforaliving@gmail.com", "ILoveToWrestle", "MySimplePasscode", "I enjoy the wrestling life", "04041999", "image", 50);
        badPasscode = new User(0, "Razor", "Ramon", "iwrestleforaliving@gmail.com", "ILove", "Wrong", "I enjoy the wrestling life", "04041999", "image", 50);
        userProfile2 = new User(1, "Razor", "Ramon", "iwrestleforaliving@gmail.com", "ILoveToWrestle", "MySimplePasscode", "I enjoy the wrestling life", "04041999", "image", 50);
        returnedProfile = new User(2, "Solomon", "Grundy", "solomon@gmail.com", "BornOnMonday", "Tuesday", "I have a poem", "04041999", "image", 50);
        otherProfile = new User(3, "Solomon", "Grundy", "solomon@gmail.com", "BornOnMonday", "Tuesday", "I have a poem", "04041999", "image", 50);
        badUsername = new User(0, "Solomon", "Grundy", "solomon@gmail.com", "IAmSolomonGrundy", "Tuesday", "I have a poem", "04041999", "image", 50);
        duplicateUsername = new User(0, "Dup", "Testing", "dup@email.com", "username", "password", "I like social media.", "04041999", "imagesrc", 50);
        usernameSpaces = new User(0, "User", "Testing", "space@email.com", "user name", "password", "I like social media.", "04041999", "imagesrc", 50);
        passwordSpaces = new User(0, "User", "Testing", "space2@email.com", "username", "password space", "I like social media.", "04041999", "imagesrc", 50);
        anotherList.add(userProfile);
        anotherList.add(userProfile2);
        blankSpaces = new User(0, "Test", "", "email@testemail.com", "", "", "Social media is fun.", "04041999", "imagesrc", 50);
    }

    //  ----------------------------------------  Mock TESTS  -------------------------------------------------

    //  --------------------------------  Username already in use  -------------------------------------
    @Test(expectedExceptions = DuplicateUsername.class, expectedExceptionsMessageRegExp = "This username is already taken")
    public void cannotHaveDuplicateUsernames() {
        Mockito.when(userDAO.createNewUser(mockUserOne)).thenThrow(new DuplicateUsername("This username is already taken"));
        userService.registerUserService(mockUserOne);
    }

    //  --------------------------------  Email already in use  -------------------------------------
    @Test(expectedExceptions = DuplicateEmail.class, expectedExceptionsMessageRegExp = "Email is already in use")
    public void cannotHaveSpacesInUsername() {
        Mockito.when(userDAO.createNewUser(duplicateEmailUser)).thenThrow(new DuplicateEmail("Email is already in use"));
        userService.registerUserService(duplicateEmailUser);
    }

    //  --------------------------------  Username has spaces  -------------------------------------
    @Test(expectedExceptions = UnallowedSpaces.class, expectedExceptionsMessageRegExp = "No spaces allowed in username or password")
    public void cannotHaveSpacesInUsernameSuccess() {
        Mockito.when(userDAO.createNewUser(usernameSpaces)).thenThrow(new UnallowedSpaces("No spaces allowed in username or password"));
        userService.registerUserService(usernameSpaces);
    }

    // SPACES IN PASSWORD
    @Test(expectedExceptions = UnallowedSpaces.class, expectedExceptionsMessageRegExp = "No spaces allowed in username or password")
    public void cannotHaveSpacesInPasswordSuccess() {
        Mockito.when(userDAO.createNewUser(passwordSpaces)).thenThrow(new UnallowedSpaces("No spaces allowed in username or password"));
        userService.registerUserService(passwordSpaces);
    }

    // BLANK INPUTS
    @Test(expectedExceptions = BlankInputs.class, expectedExceptionsMessageRegExp = "Please fill in the blanks")
    public void missingInputsForUserRegistrationSuccess() {
        Mockito.when(userDAO.createNewUser(blankSpaces)).thenThrow(new BlankInputs("Please fill in the blanks"));
        userService.registerUserService(blankSpaces);
    }

    // Get All
    @Test
    public void getAllUsersMockito() {
        Mockito.when(userDAO.getAllUsers()).thenReturn(newList);
        List<User> result = userService.getAllUsersService();
        Assert.assertEquals(result, newList);
    }

    @Test
    public void getAllUsersTwoMockito() {
        Mockito.when(userDAO.getAllUsers()).thenReturn(anotherList);
        List<User> result = userService.getAllUsersService();
        Assert.assertEquals(result, anotherList);
    }

    // REQUEST LOGIN
    @Test(expectedExceptions = BlankInputs.class, expectedExceptionsMessageRegExp = "You must enter username and password")
    public void loginServiceFailEmptyCredentials() {
        userService.loginService("", "");
    }

    @Test(expectedExceptions = TooManyChars.class, expectedExceptionsMessageRegExp = "You are exceeding your character limit")
    public void loginServiceFailLongCredentials() {
        userService.loginService("123456789012345678901234567890123455786829034586902348690324806823904608932406892309486098209234068809", "hello");
    }

    // GET USER BY ID
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input: UserId Must Be A Non 0 Positive")
    public void getUserByIdServiceFailInvalidUserId() {
        userService.getUserByIdService(0);
    }

    // SEARCH FOR USER BY USERNAME
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input: Empty Username")
    public void searchForUserServiceFailEmptyUsername() {
        userService.searchForUsersService("");

    }

    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input: UserName Exceeds 50 Characters")
    public void searchForUserServiceFailLongUsername() {
        userService.searchForUsersService("123456789012345678901234567890123455786829034586902348690324806823904608932406892309486098209234068809");
    }
}
