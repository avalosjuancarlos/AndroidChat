package edu.galileo.android.androidchat.addcontact.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.androidchat.addcontact.AddContactInteractor;
import edu.galileo.android.androidchat.addcontact.AddContactInteractorImpl;
import edu.galileo.android.androidchat.addcontact.AddContactPresenter;
import edu.galileo.android.androidchat.addcontact.AddContactPresenterImpl;
import edu.galileo.android.androidchat.addcontact.AddContactRepository;
import edu.galileo.android.androidchat.addcontact.AddContactRepositoryImpl;
import edu.galileo.android.androidchat.addcontact.ui.AddContactView;
import edu.galileo.android.androidchat.domain.FirebaseHelper;
import edu.galileo.android.androidchat.lib.EventBus;

/**
 * Created by avalo.
 */
@Module
public class AddContactModule {
    private AddContactView view;

    public AddContactModule(AddContactView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    AddContactView providesAddContactView(){
        return this.view;
    }

    @Provides
    @Singleton
    AddContactPresenter providesAddContactPresenter(AddContactView view, EventBus eventBus, AddContactInteractor interactor){
        return new AddContactPresenterImpl(view, eventBus, interactor);
    }

    @Provides
    @Singleton
    AddContactInteractor providesAddContactInteractor(AddContactRepository repository){
        return new AddContactInteractorImpl(repository);
    }

    @Provides
    @Singleton
    AddContactRepository providesAddContactRepository(FirebaseHelper helper, EventBus eventBus){
        return new AddContactRepositoryImpl(helper, eventBus);
    }
}
