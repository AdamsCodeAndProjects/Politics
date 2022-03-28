package Controllers;

import Entities.User;
import com.google.gson.Gson;
import customException.*;
import io.javalin.http.Handler;
import service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register User
    public Handler registerUser = ctx -> {
        Gson gson = new Gson();
        try {
            User newUser = gson.fromJson(ctx.body(), User.class);
            User registeredUser = this.userService.registerUserService(newUser);
            if(registeredUser == null) {
                HashMap<String, String> message = new HashMap<>();
                message.put("errorMessage", "Error processing request");
                ctx.result(gson.toJson(message));
                ctx.status(400);
            }
            String newUserJson = gson.toJson(registeredUser);
            ctx.result(gson.toJson(newUserJson));
            ctx.status(201);
        } catch (BlankInputs e) {
            HashMap<String, String> message = new HashMap<>();
            message.put("errorMessage", e.getMessage());
            ctx.result(gson.toJson(message));
            ctx.status(400);
        } catch (UnallowedSpaces e) {
            HashMap<String, String> message = new HashMap<>();
            message.put("errorMessage", e.getMessage());
            ctx.result(gson.toJson(message));
            ctx.status(400);
        } catch (DuplicateEmail e) {
            HashMap<String, String> message = new HashMap<>();
            message.put("errorMessage", e.getMessage());
            ctx.result(gson.toJson(message));
            ctx.status(400);
        } catch (DuplicateUsername e) {
            HashMap<String, String> message = new HashMap<>();
            message.put("errorMessage", e.getMessage());
            ctx.result(gson.toJson(message));
            ctx.status(400);
        }
    };

    // Search By Username
    public Handler searchForUser = ctx -> {
        String username = ctx.pathParam("username");
        Gson gson = new Gson();
        try {
            ArrayList<User> users = this.userService.searchForUsersService(username);
            if(users == null) {
                HashMap<String, String> message = new HashMap<>();
                message.put("errorMessage", "Error processing request");
                ctx.result(gson.toJson(message));
                ctx.status(400);
            }
            HashMap<String, ArrayList<User>> map = new HashMap<>();
            map.put("searchResult", users);
            String userJSON = gson.toJson(map);
            ctx.result(userJSON);
            ctx.status(200);
        } catch (InvalidInputException e) {
            HashMap<String, String> message = new HashMap<>();
            message.put("errorMessage", e.getMessage());
            ctx.result(gson.toJson(message));
            ctx.status(400);
        }
    };

    // Get All Users
    public Handler getAllUsers = ctx -> {
        Gson gson = new Gson();
        try {
            List<User> users = this.userService.getAllUsersService();
            if (users == null) {
                HashMap<String, String> message = new HashMap<>();
                message.put("errorMessage", "Error processing request");
                ctx.result(gson.toJson(message));
                ctx.status(400);
            }
            String usersJSONs = gson.toJson(users);
            ctx.result(usersJSONs);
            ctx.status(200);
        } catch (UserNotFound e) {
            HashMap<String, String> message = new HashMap<>();
            message.put("errorMessage", e.getMessage());
            ctx.result(gson.toJson(message));
            ctx.status(400);
        }
    };

    // Get Usr By Id
    public Handler getUserById = ctx -> {
        int userId = Integer.parseInt(ctx.pathParam("userId"));
        Gson gson = new Gson();
        try {
            User user = this.userService.getUserByIdService(userId);
            if(user == null) {
                HashMap<String, String> message = new HashMap<>();
                message.put("errorMessage", "Error processing request");
                ctx.result(gson.toJson(message));
                ctx.status(400);
            } else {
                String userJSON = gson.toJson(user);
                ctx.result(userJSON);
                ctx.status(200);
            }
        } catch (InvalidInputException e) {
            HashMap<String, String> message = new HashMap<>();
            message.put("errorMessage", e.getMessage());
            ctx.result(gson.toJson(message));
            ctx.status(400);
        } catch (UserNotFound e) {
            HashMap<String, String> message = new HashMap<>();
            message.put("errorMessage", e.getMessage());
            ctx.result(gson.toJson(message));
            ctx.status(400);
        } catch (UsernameOrPasscodeException e) {
            HashMap<String, String> message = new HashMap<>();
            message.put("errorMessage", e.getMessage());
            ctx.result(gson.toJson(message));
            ctx.status(400);
        }
    };

    //  Login

    public Handler login = ctx -> {
        Gson gson = new Gson();
        try {
            User credentials = gson.fromJson(ctx.body(), User.class);
            User user = this.userService.loginService(credentials.getUsername(), credentials.getPasscode());
            if (user == null) {
                HashMap<String, String> message = new HashMap<>();
                message.put("errorMessage", "Error processing request");
                ctx.result(gson.toJson(message));
                ctx.status(400);
            }
            String userLoginJSON = gson.toJson(user);
            ctx.result(userLoginJSON);
            ctx.status(200);
        } catch (Exception e) {
            HashMap<String, String> message = new HashMap<>();
            message.put("errorMessage", e.getMessage());
            ctx.result(gson.toJson(message));
            ctx.status(400);
        }
    };
}
