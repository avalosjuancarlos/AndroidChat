package edu.galileo.android.androidchat.addcontact.events;

/**
 * Created by avalo.
 */
public class AddContactEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
