package edu.galileo.android.androidchat.signup.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.androidchat.AndroidChatApplicationModule;
import edu.galileo.android.androidchat.domain.di.DomainModule;
import edu.galileo.android.androidchat.lib.di.LibModule;
import edu.galileo.android.androidchat.login.di.LoginModule;
import edu.galileo.android.androidchat.signup.ui.SignUpActivity;

/**
 * Created by avalo.
 */
@Singleton
@Component(modules = {LoginModule.class, DomainModule.class, LibModule.class, AndroidChatApplicationModule.class})
public interface SignUpComponent {
    void inject(SignUpActivity activity);
}
