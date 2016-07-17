package edu.galileo.android.androidchat.login.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.androidchat.AndroidChatApplicationModule;
import edu.galileo.android.androidchat.domain.di.DomainModule;
import edu.galileo.android.androidchat.lib.di.LibModule;
import edu.galileo.android.androidchat.login.ui.LoginActivity;

/**
 * Created by avalo.
 */
@Singleton
@Component(modules = {LoginModule.class, DomainModule.class, LibModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
