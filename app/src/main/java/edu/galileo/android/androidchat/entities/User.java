package edu.galileo.android.androidchat.entities;

import java.util.Map;
/**
 * Created by avalo.
 */
public class User {
    String email;
    boolean online;
    Map<String, Boolean> contacts;

    public static final boolean ONLINE = true;
    public static final boolean OFFLINE = false;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object object) {
        boolean equal = false;

        if(object instanceof User){
            User user = (User) object;
            equal = this.email.equals(user.getEmail());
        }

        return equal;
    }
}
