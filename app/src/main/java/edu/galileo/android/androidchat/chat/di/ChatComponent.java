package edu.galileo.android.androidchat.chat.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.androidchat.AndroidChatApplicationModule;
import edu.galileo.android.androidchat.chat.ui.ChatActivity;
import edu.galileo.android.androidchat.domain.di.DomainModule;
import edu.galileo.android.androidchat.lib.di.LibModule;

/**
 * Created by avalo.
 */
@Singleton
@Component(modules = {ChatModule.class, DomainModule.class, LibModule.class})
public interface ChatComponent {
    void inject(ChatActivity chatActivity);
}
