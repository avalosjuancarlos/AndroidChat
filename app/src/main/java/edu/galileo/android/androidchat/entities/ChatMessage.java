package edu.galileo.android.androidchat.entities;

import com.google.firebase.database.Exclude;

/**
 * Created by avalo.
 */
public class ChatMessage {
    private String msg;
    private String sender;
    @Exclude
    private boolean sentByMe;

    public ChatMessage() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isSentByMe() {
        return sentByMe;
    }

    public void setSentByMe(boolean sentByMe) {
        this.sentByMe = sentByMe;
    }

    @Override
    public boolean equals(Object object) {
        boolean equal = false;

        if(object instanceof  ChatMessage){
            ChatMessage msg = (ChatMessage) object;
            equal = this.sender.equals(msg.getSender()) && this.msg.equals(msg.getMsg()) && this.sentByMe == msg.isSentByMe();
        }

        return equal;
    }
}
