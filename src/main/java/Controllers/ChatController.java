package Controllers;

import io.javalin.websocket.WsContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatController {

    private Map<WsContext, Map> usernameMap = new ConcurrentHashMap<>();

//    private ChatService chatService;
//
//    public ChatController(ChatService chatService) {
//        this.chatService = chatService;
//    }

//    public void connectToWebSocket(WsConfig websocket) {
//        websocket.onConnect(ctx -> {
//            ArrayList<Chat> messages;
//            int groupId=Integer.parseInt(ctx.pathParam("id"));
//            String userName=ctx.pathParam("userName");
//            Map<String, Object> userInfo = new HashMap<>();
//            userInfo.put("groupId", groupId);
//            userInfo.put("userName", userName);
//            usernameMap.put(ctx, userInfo);
//            userListBroadcast(groupId);
//            if(groupId == 0) {
//                messages=chatService.serviceGetMessageHistory();
//            } else {
//                messages=chatService.serviceGetMessageHistory(groupId);
//            }
//            if(messages!=null) {
//                for(Chat message : messages) {
//                    broadcastMessage(message.getChatId(), message.getUserId(), message.getChatContent(),
//                            message.getUserName(), message.getChatDate(), message.getGroupId());
//                }
//            }
//        });
//        websocket.onClose(ctx -> {
//            usernameMap.remove(ctx);
//            int groupId=Integer.parseInt(ctx.pathParam("id"));
//            userListBroadcast(groupId);
//        });
//
//        websocket.onMessage(ctx -> {
//            System.out.println(ctx.message());
//            Gson gson = new Gson();
//            Map<Object, String> messageJson = gson.fromJson(ctx.message(), Map.class);
//            System.out.println(messageJson);
//            Double userIdDouble = Double.parseDouble(messageJson.get("userId"));
//            int userId = userIdDouble.intValue();
//            int groupId = (Integer) usernameMap.get(ctx).get("groupId");
//
//            String chatContent = messageJson.get("chatContent");
//            String userName = messageJson.get("username");
//            Chat chatMessage = new Chat(userId, groupId, chatContent);
//            Chat returnedChat = chatService.serviceCreateMessageObject(chatMessage);
//            broadcastMessage(returnedChat.getChatId(), returnedChat.getUserId(), returnedChat.getChatContent(),
//                    username, returnedChat.getChatDate(), groupId);
//        });
//        websocket.onBinaryMessage(ctx -> {
//            int groupId = Integer.parseInt(ctx.pathParam("id"));
//            String userName = ctx.pathParam("username");
//            ctx.data();
//            sendImg(ctx.data(), groupId, userName);
//        });
//    };
//
//    public void broadcastMessage(int chatId, int userId, String chatContent, String userName, String date,
//                                 int groupId) {
//        usernameMap.keySet().stream().filer(ctx -> (ctx.session.isOpen() && (Integer) usernameMap.get("groupId") == groupId)).forEach(session -> {
//            System.out.println(session);
//            Gson gson = new Gson();
//            Map<String, Object> broadcastString = new HashMap<>();
//            broadcastString.put("chatId", chatId);
//            broadcastString.put("userId", userId);
//            broadcastString.put("chatContent", chatContent);
//            broadcastString.put("username", username);
//            broadcastString.put("date", date);
//            session.send(gson.toJson(broadcastString));
//        });
//    }
//
//    public void userListBroadcast(int groupId) {
//        ArrayList<String> userList = new ArrayList();
//        usernameMap.keySet().stream.filter(ctx -> (ctx.session.isOpen() && (Integer) usernameMap.get(ctx).get("groupId") == groupId)).forEach(session-> {
//            System.out.println(session);
//            Map<String, Object> broadcastString = new HashMap<>();
//            broadcastString.put("imgContent", imgSent);
//            broadcastString.put("username", username);
//            session.send(broadcastString);
//        });
//    }
}
