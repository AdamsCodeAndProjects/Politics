package AppMain;

import Controllers.UserController;
import dao.imp.UserDAO;
import io.javalin.Javalin;
import service.UserService;

public class MainApp {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
           config.enableCorsForAllOrigins();
           config.enableDevLogging();
        });

        // -----------------------Uncomment when chat is up---------------------------------
//        ChatDAO chatDAO = new ChatDAO();
//        ChatService chatService = new ChatService(chatDAO);
//        ChatController chatController = new ChatController(chatService);

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserController userController = new UserController(userService);

//        AppController appController = new AppController(app, chatController, userController);

//        appController.createChatRoutes();
//        appController.createUserRoutes();

        app.start();
    }
}
