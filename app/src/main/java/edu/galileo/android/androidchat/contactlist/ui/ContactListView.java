package edu.galileo.android.androidchat.contactlist.ui;

import edu.galileo.android.androidchat.entities.User;

/**
 * Created by avalo.
 */
public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
