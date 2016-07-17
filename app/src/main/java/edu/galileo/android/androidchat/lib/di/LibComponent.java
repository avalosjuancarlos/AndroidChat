package edu.galileo.android.androidchat.lib.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.androidchat.AndroidChatApplicationModule;

/**
 * Created by avalo.
 */
@Singleton
@Component(modules = {LibModule.class, AndroidChatApplicationModule.class})
public interface LibComponent {
}
