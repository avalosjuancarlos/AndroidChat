package edu.galileo.android.androidchat.chat.events;

import edu.galileo.android.androidchat.entities.ChatMessage;

/**
 * Created by avalo.
 */
public class ChatEvent {
    private ChatMessage message;

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}
