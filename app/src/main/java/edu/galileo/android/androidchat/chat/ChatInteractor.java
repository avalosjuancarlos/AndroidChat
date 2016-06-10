package edu.galileo.android.androidchat.chat;

/**
 * Created by avalo.
 */
public interface ChatInteractor {
    void sendMessage(String msg);
    void setRecipient(String recipient);

    void subscribe();
    void unsubscribe();
    void destroyListener();
}
