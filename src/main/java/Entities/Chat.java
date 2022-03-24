package Entities;

import java.util.Objects;

public class Chat {

    private int chatId;
    private String chatDate;
    private int userId;
    private String username;
    private int groupId;
    private String chatContent;

    public Chat() {}

    public Chat(int userId, int groupId, String chatContent){
        this.userId = userId;
        this.groupId = groupId;
        this.chatContent = chatContent;
    }

    public Chat(int chatId, String chatDate, int userId, String username, int groupId, String chatContent) {
        this.chatId = chatId;
        this.chatDate = chatDate;
        this.userId = userId;
        this.username = username;
        this.groupId = groupId;
        this.chatContent = chatContent;
    }

    // toString
    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", chatDate='" + chatDate + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", groupId=" + groupId +
                ", chatContent='" + chatContent + '\'' +
                '}';
    }

    // Hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return chatId == chat.chatId && userId == chat.userId && groupId == chat.groupId && chatDate.equals(chat.chatDate) && username.equals(chat.username) && chatContent.equals(chat.chatContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, chatDate, userId, username, groupId, chatContent);
    }

    // Getters and Setters

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getChatDate() {
        return chatDate;
    }

    public void setChatDate(String chatDate) {
        this.chatDate = chatDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }
}
