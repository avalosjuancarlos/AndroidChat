package edu.galileo.android.androidchat;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by avalo.
 */
@Module
public class AndroidChatApplicationModule {
    private AndroidChatApplication app;

    public AndroidChatApplicationModule(AndroidChatApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context providesApplicationContext(){
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    AndroidChatApplication providesAndroidChatApplication(){
        return this.app;
    }
}
