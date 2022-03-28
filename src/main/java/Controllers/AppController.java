package Controllers;

import io.javalin.Javalin;

public class AppController {
    private ChatController chatController;
    private UserController userController;
    private Javalin app;

    public AppController(Javalin app, ChatController chatController, UserController userController)  {
        this.chatController = chatController;
        this.userController = userController;
        this.app = app;
    }

    //For chat
    public void createChatRoutes() {
        app.ws("/chat/{id}/{userName}", chatController::connectToWebSocket);
    }

    public void createUserRoutes() {
        app.get("/user/search/{username}", userController.searchForUser);
        app.get("user/{userId}", userController.getUserById);
        app.get("/users", userController.getAllUsers);
        app.post("/user/login", userController.login);
        app.get("/user/registration", userController.registerUser);
    }
}
