package edu.galileo.android.androidchat.chat.ui;

import edu.galileo.android.androidchat.entities.ChatMessage;

/**
 * Created by avalo.
 */
public interface ChatView {
    void onMessageReceived(ChatMessage msg);
}
