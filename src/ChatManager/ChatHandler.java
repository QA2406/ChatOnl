package ChatManager;

public class ChatHandler {
    
    private String username;
    private StringBuilder chat;

    public ChatHandler(String username, StringBuilder chat) {
        this.username = username;
        this.chat = chat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public StringBuilder getChat() {
        return chat;
    }

    public void setChat(StringBuilder chat) {
        this.chat= chat;
    }
    
    
}