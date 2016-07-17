package edu.galileo.android.androidchat.domain;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import edu.galileo.android.androidchat.BuildConfig;
import edu.galileo.android.androidchat.entities.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by avalo.
 */
public class FirebaseHelper {
    private static final String TAG = "FirebaseHelper";

    private DatabaseReference dataReference;
    private static final String SEPARATOR = "__";
    private static final String CHATS_PATH = "chats";
    private static final String USERS_PATH = "users";
    private static final String CONTACTS_PATH = "contacts";
    private static final String TOPIC_SEPARATOR = "__from__";


    public FirebaseHelper(DatabaseReference databaseReference){
        this.dataReference = databaseReference;
        this.dataReference.keepSynced(true); // Keeping Data Fresh
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

    public void subscribeToTopic(String receiver){
        String topic = makeNotificationTopic(receiver, true);

        FirebaseMessaging.getInstance().subscribeToTopic(topic);

        Log.d(TAG, topic);
    }

    public void sendNotificationTopic(final String receiver, String msg){
        String topic = makeNotificationTopic(receiver, false);

        MediaType JSON = MediaType.parse("application/json");
        String json = "{\"to\": \"/topics/" + topic + "\",\"data\": {\"message\": \"" + msg + "\"}}";

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "key=" + BuildConfig.FIREBASE_SERVER_ID)
                .url(BuildConfig.FCM_SEND_URL)
                .post(body)
                .build();

        Log.d(TAG, json);

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, e.getLocalizedMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d(TAG, response.message());
                }
            });

    }

    private String makeNotificationTopic(String receiver, boolean reverse){
        String keySender = getAuthUserEmail().replace(".", "_");
        String keyReceiver = receiver.replace(".", "_");

        // allowed format [a-zA-Z0-9-_.~%]{1,900}
        if(reverse) {
            return (keySender + TOPIC_SEPARATOR + keyReceiver).replace("@", "_at_");
        }
        return (keyReceiver + TOPIC_SEPARATOR + keySender).replace("@", "_at_");
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
