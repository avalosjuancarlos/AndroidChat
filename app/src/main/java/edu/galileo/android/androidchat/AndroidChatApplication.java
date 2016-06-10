package edu.galileo.android.androidchat;

import android.app.Application;

import com.firebase.client.Firebase;

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
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
