package edu.galileo.android.androidchat.domain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import edu.galileo.android.androidchat.entities.User;

/**
 * Created by avalo.
 */
public class FirebaseHelper {
    private DatabaseReference dataReference;
    private static final String SEPARATOR = "__";
    private static final String CHATS_PATH = "chats";
    private static final String USERS_PATH = "users";
    private static final String CONTACTS_PATH = "contacts";

    private static class SingletonHolder {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance(){
            return SingletonHolder.INSTANCE;
    }

    public FirebaseHelper(){
        this.dataReference = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDataReference(){
        return dataReference;
    }

    public String getAuthUserEmail(){
        FirebaseAuth authData = FirebaseAuth.getInstance();
        String email = null;
        FirebaseUser providerData = authData.getCurrentUser();
        if(providerData != null){
            email = providerData.getEmail();
        }

        return email;
    }

    public DatabaseReference getUserReference(String email){
        DatabaseReference userReference = null;
        if(email != null){
            String emailKey = email.replace(".", "_");
            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return userReference;
    }

    public DatabaseReference getMyUserReference(){
        return getUserReference(getAuthUserEmail());
    }

    public DatabaseReference getContactsReference(String email){
        return getUserReference(email).child(CONTACTS_PATH);
    }

    public DatabaseReference getMyContactsReference(){
        return getContactsReference(getAuthUserEmail());
    }

    public DatabaseReference getOneContactReference(String mainEmail, String childEmail){
        String childKey = childEmail.replace(".", "_");
        return getUserReference(mainEmail).child(CONTACTS_PATH).child(childKey);
    }

    public DatabaseReference getChatsReference(String receiver){
        String keySender = getAuthUserEmail().replace(".", "_");
        String keyReceiver = receiver.replace(".", "_");

        String keyChat = keySender + SEPARATOR + keyReceiver;
        if(keySender.compareTo(keyReceiver) > 0){
            keyChat = keyReceiver + SEPARATOR + keySender;
        }
        return dataReference.getRoot().child(CHATS_PATH).child(keyChat);
    }

    public void changeUserConnectionStatus(boolean online){
        if(getMyUserReference() != null){
            Map<String, Object> updates = new HashMap<>();
            updates.put("online", online);
            getMyUserReference().updateChildren(updates);
            notifyContactsOfConnectionChange(online);
        }

    }

    public void notifyContactsOfConnectionChange(boolean online) {
        notifyContactsOfConnectionChange(online, false);
    }

    public void signOff(){
        notifyContactsOfConnectionChange(User.OFFLINE, true);
    }

    private void notifyContactsOfConnectionChange(final boolean online,final boolean signOff) {
        final String myEmail = getAuthUserEmail();
        getMyContactsReference().addListenerForSingleValueEvent(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        String email = child.getKey();
                        DatabaseReference reference = getOneContactReference(email, myEmail);
                        reference.setValue(online);
                    }
                    if(signOff){
                        FirebaseAuth.getInstance().signOut();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
