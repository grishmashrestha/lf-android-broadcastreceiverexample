package com.example.grishma.broadcastreceiverexample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.Random;

/**
 * Created by grishma on 2/22/16.
 */
public class BroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("abc")) {
            Log.e("BroadcastReceiver", "myAction was sent");
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.whale)
                    .setContentTitle("Test Notification")
                    .setContentText("Hello World!");

            Intent resultIntent = new Intent(context, MainActivity.class);

            PendingIntent resultPendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );

            builder.setContentIntent(resultPendingIntent);

            NotificationManager managerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Random rand = null;
            rand=new Random();
            managerCompat.notify(rand.nextInt((100 - 1) + 1) + 1, builder.build());

        }
    }

}
