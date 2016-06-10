package edu.galileo.android.androidchat.chat;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.androidchat.chat.events.ChatEvent;
import edu.galileo.android.androidchat.chat.ui.ChatView;
import edu.galileo.android.androidchat.entities.User;
import edu.galileo.android.androidchat.lib.EventBus;
import edu.galileo.android.androidchat.lib.GreenRobotEventBus;

/**
 * Created by avalo.
 */
public class chatPresenterImpl implements ChatPresenter {
    private EventBus eventBus;
    private ChatView view;
    private ChatInteractor chatInteractor;
    private ChatSessionInteractor sessionInteractor;

    public chatPresenterImpl(ChatView view) {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.chatInteractor = new ChatInteractorImpl();
        this.sessionInteractor = new ChatSessionInteractorImpl();
        this.view = view;
    }

    @Override
    public void onPause() {
        chatInteractor.unsubscribe();
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onResume() {
        chatInteractor.subscribe();
        sessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
        chatInteractor.destroyListener();
    }

    @Override
    public void setChatRecipient(String recipient) {
        chatInteractor.setRecipient(recipient);
    }

    @Override
    public void sendMessage(String msg) {
        chatInteractor.sendMessage(msg);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ChatEvent event) {
        if (view != null){
            view.onMessageReceived(event.getMessage());
        }
    }
}
