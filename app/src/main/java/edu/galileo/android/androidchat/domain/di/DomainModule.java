package edu.galileo.android.androidchat.domain.di;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.androidchat.domain.AvatarHelper;
import edu.galileo.android.androidchat.domain.FirebaseHelper;

/**
 * Created by avalo.
 */
@Module
public class DomainModule {

    @Provides
    @Singleton
    AvatarHelper providesAvatarHelper(){
        return new AvatarHelper();
    }

    @Provides
    @Singleton
    DatabaseReference providesDatabaseReference(){
        return FirebaseDatabase.getInstance().getReference();
    }

    @Provides
    @Singleton
    FirebaseHelper providesFirebaseHelper(DatabaseReference databaseReference){
        return new FirebaseHelper(databaseReference);
    }
}
