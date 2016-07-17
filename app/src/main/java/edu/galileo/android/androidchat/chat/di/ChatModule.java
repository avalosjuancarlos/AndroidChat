package edu.galileo.android.androidchat.chat.di;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.androidchat.chat.ChatInteractor;
import edu.galileo.android.androidchat.chat.ChatInteractorImpl;
import edu.galileo.android.androidchat.chat.ChatPresenter;
import edu.galileo.android.androidchat.chat.ChatPresenterImpl;
import edu.galileo.android.androidchat.chat.ChatRepository;
import edu.galileo.android.androidchat.chat.ChatRepositoryImpl;
import edu.galileo.android.androidchat.chat.ChatSessionInteractor;
import edu.galileo.android.androidchat.chat.ChatSessionInteractorImpl;
import edu.galileo.android.androidchat.chat.ui.ChatView;
import edu.galileo.android.androidchat.chat.ui.adapters.ChatAdapter;
import edu.galileo.android.androidchat.domain.FirebaseHelper;
import edu.galileo.android.androidchat.entities.ChatMessage;
import edu.galileo.android.androidchat.lib.EventBus;

/**
 * Created by avalo.
 */
@Module
public class ChatModule {
    private ChatView view;
    private Context context;

    public ChatModule(ChatView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Provides
    @Singleton
    ChatView providesChatView(){
        return this.view;
    }

    @Provides
    @Singleton
    Context providesContext(){
        return this.context;
    }

    @Provides
    @Singleton
    ChatPresenter providesChatPresenter(ChatView view, EventBus eventBus, ChatInteractor chatInteractor, ChatSessionInteractor sessionInteractor){
        return new ChatPresenterImpl(view, eventBus, chatInteractor, sessionInteractor);
    }

    @Provides
    @Singleton
    ChatInteractor providesChatInteractor(ChatRepository repository){
        return new ChatInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ChatSessionInteractor providesChatSessionInteractor(ChatRepository repository){
        return new ChatSessionInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ChatRepository providesChatRepository(EventBus eventBus, FirebaseHelper helper){
        return new ChatRepositoryImpl(eventBus, helper);
    }

    @Provides
    @Singleton
    ChatAdapter providesChatAdapter(Context context, List<ChatMessage> chatMessages){
        return new ChatAdapter(context, chatMessages);
    }

    @Provides
    @Singleton
    List<ChatMessage> providesListChatMessages(){
        return new ArrayList<>();
    }

}
