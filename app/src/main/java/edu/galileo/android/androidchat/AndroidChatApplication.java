package edu.galileo.android.androidchat;

import android.app.Application;
import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

import edu.galileo.android.androidchat.addcontact.di.AddContactComponent;
import edu.galileo.android.androidchat.addcontact.di.AddContactModule;
import edu.galileo.android.androidchat.addcontact.di.DaggerAddContactComponent;
import edu.galileo.android.androidchat.addcontact.ui.AddContactView;
import edu.galileo.android.androidchat.chat.di.ChatComponent;
import edu.galileo.android.androidchat.chat.di.ChatModule;
import edu.galileo.android.androidchat.chat.di.DaggerChatComponent;
import edu.galileo.android.androidchat.chat.ui.ChatView;
import edu.galileo.android.androidchat.contactlist.di.ContactListComponent;
import edu.galileo.android.androidchat.contactlist.di.ContactListModule;
import edu.galileo.android.androidchat.contactlist.di.DaggerContactListComponent;
import edu.galileo.android.androidchat.contactlist.ui.ContactListView;
import edu.galileo.android.androidchat.contactlist.ui.adapters.OnItemClickListener;
import edu.galileo.android.androidchat.domain.di.DomainModule;
import edu.galileo.android.androidchat.lib.di.LibModule;
import edu.galileo.android.androidchat.login.di.DaggerLoginComponent;
import edu.galileo.android.androidchat.login.di.LoginComponent;
import edu.galileo.android.androidchat.login.di.LoginModule;
import edu.galileo.android.androidchat.login.ui.LoginView;
import edu.galileo.android.androidchat.signup.di.DaggerSignUpComponent;
import edu.galileo.android.androidchat.signup.di.SignUpComponent;


/**
 * Created by avalo.
 */
public class AndroidChatApplication extends Application {

    private AndroidChatApplicationModule androidChatApplicationModule;
    private DomainModule domainModule;
    private LibModule libModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initFirebase();
        initModules();
    }

    private void initFirebase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true); // Disk Persistence
    }

    private void initModules() {
        androidChatApplicationModule = new AndroidChatApplicationModule(this);
        domainModule = new DomainModule();
        libModule = new LibModule();
    }

    public LoginComponent getLoginComponent(LoginView view){

        return DaggerLoginComponent
                .builder()
                .domainModule(domainModule)
                .libModule(libModule)
                .loginModule(new LoginModule(view))
                .build();
    }

    public SignUpComponent getSignUpComponent(LoginView view){

        return DaggerSignUpComponent
                .builder()
                .domainModule(domainModule)
                .libModule(libModule)
                .loginModule(new LoginModule(view))
                .build();
    }

    public AddContactComponent getAddContactComponent(AddContactView view){

        return DaggerAddContactComponent
                .builder()
                .domainModule(domainModule)
                .libModule(libModule)
                .addContactModule(new AddContactModule(view))
                .build();
    }

    public ChatComponent getChatComponent(ChatView view, Context context){
        return DaggerChatComponent
                .builder()
                .domainModule(domainModule)
                .libModule(libModule)
                .chatModule(new ChatModule(view, context))
                .build();
    }

    public ContactListComponent getContactListComponent(ContactListView contactListView,
                                                        OnItemClickListener onItemClickListener){
        return DaggerContactListComponent
                .builder()
                .androidChatApplicationModule(androidChatApplicationModule)
                .domainModule(domainModule)
                .libModule(libModule)
                .contactListModule(new ContactListModule(contactListView, onItemClickListener))
                .build();
    }
}
