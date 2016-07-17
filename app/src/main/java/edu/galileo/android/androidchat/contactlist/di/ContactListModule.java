package edu.galileo.android.androidchat.contactlist.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.androidchat.contactlist.ContactListInteractor;
import edu.galileo.android.androidchat.contactlist.ContactListInteractorImpl;
import edu.galileo.android.androidchat.contactlist.ContactListPresenter;
import edu.galileo.android.androidchat.contactlist.ContactListPresenterImpl;
import edu.galileo.android.androidchat.contactlist.ContactListRepository;
import edu.galileo.android.androidchat.contactlist.ContactListRepositoryImpl;
import edu.galileo.android.androidchat.contactlist.ContactListSessionInteractor;
import edu.galileo.android.androidchat.contactlist.ContactListSessionInteractorImpl;
import edu.galileo.android.androidchat.contactlist.ui.ContactListView;
import edu.galileo.android.androidchat.contactlist.ui.adapters.ContactListAdapter;
import edu.galileo.android.androidchat.contactlist.ui.adapters.OnItemClickListener;
import edu.galileo.android.androidchat.domain.FirebaseHelper;
import edu.galileo.android.androidchat.entities.User;
import edu.galileo.android.androidchat.lib.EventBus;
import edu.galileo.android.androidchat.lib.ImageLoader;

/**
 * Created by avalo.
 */
@Module
public class ContactListModule {

    private ContactListView contactListView;
    private OnItemClickListener onItemClickListener;

    public ContactListModule(ContactListView contactListView, OnItemClickListener onItemClickListener) {
        this.contactListView = contactListView;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    ContactListView providesContactListView(){
        return this.contactListView;
    }

    @Provides
    @Singleton
    ContactListPresenter providesContactListPresenter(ContactListView view, EventBus eventBus, ContactListInteractor listInteractor, ContactListSessionInteractor sessionInteractor){
        return new ContactListPresenterImpl(view, eventBus, listInteractor, sessionInteractor);
    }

    @Provides
    @Singleton
    ContactListInteractor providesContactListInteractor(ContactListRepository repository){
        return new ContactListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ContactListSessionInteractor providesContactListSessionInteractor(ContactListRepository repository){
        return new ContactListSessionInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ContactListRepository providesContactListRepository(FirebaseHelper helper, EventBus eventBus){
        return new ContactListRepositoryImpl(helper, eventBus);
    }

    @Provides
    @Singleton
    ContactListAdapter providesContactListAdapter(List<User> contactList, ImageLoader imageLoader, OnItemClickListener onItemClickListener){
        return new ContactListAdapter(contactList, imageLoader, onItemClickListener);
    }

    @Provides
    @Singleton
    List<User> providesListUsers(){
        return new ArrayList<>();
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.onItemClickListener;
    }
}
