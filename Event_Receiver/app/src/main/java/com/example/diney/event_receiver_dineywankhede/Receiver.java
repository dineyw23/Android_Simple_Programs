package com.example.diney.event_receiver_dineywankhede;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.provider.Settings;
import android.annotation.SuppressLint;


/**
 * Created by diney on 2/22/15.
 */
public class Receiver extends BroadcastReceiver{


    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public void onReceive(Context context, Intent intent) {
        boolean isEnabled = Settings.Global.getInt(context.getContentResolver(),Settings.Global.AIRPLANE_MODE_ON, 0) == 1;
        if(isEnabled)
        {
            Toast.makeText(context, "AIRPLANE MODE ON", Toast.LENGTH_SHORT).show();
            MainActivity.sendNotification(context, "FLIGHT MODE ON");

        }
        else
        {
            Toast.makeText(context, "AIRPLANE MODE OFF", Toast.LENGTH_SHORT).show();
            MainActivity.sendNotification(context, "FLIGHT MODE OFF");

        }

}

}
