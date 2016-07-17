package edu.galileo.android.androidchat.login.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.androidchat.domain.FirebaseHelper;
import edu.galileo.android.androidchat.lib.EventBus;
import edu.galileo.android.androidchat.login.LoginInteractor;
import edu.galileo.android.androidchat.login.LoginInteractorImpl;
import edu.galileo.android.androidchat.login.LoginPresenter;
import edu.galileo.android.androidchat.login.LoginPresenterImpl;
import edu.galileo.android.androidchat.login.LoginRepository;
import edu.galileo.android.androidchat.login.LoginRepositoryImpl;
import edu.galileo.android.androidchat.login.ui.LoginView;

/**
 * Created by avalo.
 */
@Module
public class LoginModule {
    LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    LoginView providesLoginView(){
        return this.view;
    }

    @Provides
    @Singleton
    LoginPresenter providesLoginPresenter(LoginView loginView, LoginInteractor loginInteractor, EventBus eventBus){
        return new LoginPresenterImpl(loginView, loginInteractor, eventBus);
    }

    @Provides
    @Singleton
    LoginInteractor providesLoginInteractor(LoginRepository loginRepository){
        return new LoginInteractorImpl(loginRepository);
    }

    @Provides
    @Singleton
    LoginRepository providesLoginRepository(FirebaseHelper helper, EventBus eventBus){
        return new LoginRepositoryImpl(helper, eventBus);
    }

}
