package edu.galileo.android.androidchat;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by avalo.
 */
public class AndroidChatApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
