package edu.galileo.android.androidchat.chat;

/**
 * Created by avalo.
 */
public class ChatSessionInteractorImpl implements ChatSessionInteractor {
    ChatRepository repository;

    public ChatSessionInteractorImpl(ChatRepository repository) {
        this.repository = repository;
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeConnectionStatus(online);
    }
}
