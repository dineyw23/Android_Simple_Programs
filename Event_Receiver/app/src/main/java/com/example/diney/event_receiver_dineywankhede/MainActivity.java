package com.example.diney.event_receiver_dineywankhede;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.net.Uri;
import android.media.RingtoneManager;

/**
 * Created by diney on 2/22/15.
 */

public class MainActivity extends Activity   {


    public static final int UniqueID = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(new Receiver(), filter);

    }

        public static void sendNotification(Context context, String msg) {

            NotificationManager nm;
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Uri alarmSound = RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_NOTIFICATION);

            PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, MainActivity.class), 0);
            NotificationCompat.Builder notification =
                    new NotificationCompat.Builder(context)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.ic_app)
                            .setContentTitle("FLIGHT MODE CHANGED")
                            .setWhen(System.currentTimeMillis())
                            .setTicker("FLIGHT MODE CHANGED")
                            .setContentText(msg)
                            .setSound(alarmSound);
            notification.setContentIntent(contentIntent);

            nm.notify(UniqueID, notification.build());

        }
}

