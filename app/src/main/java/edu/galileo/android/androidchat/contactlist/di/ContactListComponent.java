package edu.galileo.android.androidchat.contactlist.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.androidchat.AndroidChatApplicationModule;
import edu.galileo.android.androidchat.contactlist.ui.ContactListActivity;
import edu.galileo.android.androidchat.domain.di.DomainModule;
import edu.galileo.android.androidchat.lib.di.LibModule;

/**
 * Created by avalo.
 */
@Singleton
@Component(modules = {ContactListModule.class, DomainModule.class, LibModule.class, AndroidChatApplicationModule.class})
public interface ContactListComponent {
    void inject(ContactListActivity contactListActivity);
}
