package edu.galileo.android.androidchat.contactlist.ui.adapters;

import edu.galileo.android.androidchat.entities.User;

/**
 * Created by avalo.
 */
public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
