package edu.galileo.android.androidchat.addcontact.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.androidchat.AndroidChatApplicationModule;
import edu.galileo.android.androidchat.addcontact.ui.AddContactFragment;
import edu.galileo.android.androidchat.domain.di.DomainModule;
import edu.galileo.android.androidchat.lib.di.LibModule;

/**
 * Created by avalo.
 */
@Singleton
@Component(modules = {AddContactModule.class, DomainModule.class, LibModule.class})
public interface AddContactComponent {
    void inject(AddContactFragment addContactFragment);
}
