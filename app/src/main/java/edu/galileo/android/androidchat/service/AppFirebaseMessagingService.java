package edu.galileo.android.androidchat.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import edu.galileo.android.androidchat.contactlist.ui.ContactListActivity;

/**
 * Created by avalo.
 */
public class AppFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "ChatFMService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Handle data payload of FCM messages.
        Log.d(TAG, "FCM Message Id: " + remoteMessage.getMessageId());
        Log.d(TAG, "FCM Notification Message: " + remoteMessage.getNotification());
        Log.d(TAG, "FCM Data Message: " + remoteMessage.getData());
        Log.d(TAG, "FCM From: " + remoteMessage.getFrom());
        Log.d(TAG, "FCM To: " + remoteMessage.getTo());
        Log.d(TAG, "FCM CollapseKey: " + remoteMessage.getCollapseKey());
        Log.d(TAG, "FCM Message Type: " + remoteMessage.getMessageType());
        Log.d(TAG, "FCM To String: " + remoteMessage.toString());
        sendNotification(remoteMessage.getData().toString());
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, ContactListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
