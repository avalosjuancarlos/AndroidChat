package edu.galileo.android.androidchat.contactlist;

/**
 * Created by avalo.
 */
public interface ContactListSessionInteractor {
    void signOff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
}
