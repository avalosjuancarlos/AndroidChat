package edu.galileo.android.androidchat.chat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import edu.galileo.android.androidchat.chat.events.ChatEvent;
import edu.galileo.android.androidchat.domain.FirebaseHelper;
import edu.galileo.android.androidchat.entities.ChatMessage;
import edu.galileo.android.androidchat.lib.EventBus;

/**
 * Created by avalo.
 */
public class ChatRepositoryImpl implements ChatRepository {
    private String recipient;
    private EventBus eventBus;
    private FirebaseHelper helper;
    private ChildEventListener chatEventListener;

    public ChatRepositoryImpl(EventBus eventBus, FirebaseHelper helper) {
        this.eventBus = eventBus;
        this.helper = helper;
    }

    @Override
    public void sendMessage(String msg) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(helper.getAuthUserEmail());
        chatMessage.setMsg(msg);

        DatabaseReference chatsReference = helper.getChatsReference(recipient);
        chatsReference.push().setValue(chatMessage);

        helper.sendNotificationTopic(recipient, msg); // Push notification
    }

    @Override
    public void setRecipient(String recipient) {
        this.recipient = recipient;

        helper.subscribeToTopic(recipient); // Notification topic
    }

    @Override
    public void subscribe() {
        if(chatEventListener == null){
            chatEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                    String msgSender = chatMessage.getSender();

                    chatMessage.setSentByMe(msgSender.equals(helper.getAuthUserEmail()));

                    ChatEvent chatEvent = new ChatEvent();
                    chatEvent.setMessage(chatMessage);
                    eventBus.post(chatEvent);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {}

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
        }

        helper.getChatsReference(recipient).addChildEventListener(chatEventListener);
    }

    @Override
    public void unsubscribe() {
        if(chatEventListener != null){
            helper.getChatsReference(recipient).removeEventListener(chatEventListener);
        }
    }

    @Override
    public void destroyListener() {
        chatEventListener = null;
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }
}
