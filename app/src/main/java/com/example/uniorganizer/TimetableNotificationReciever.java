package com.example.uniorganizer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimetableNotificationReciever extends BroadcastReceiver {
    public TimetableNotificationReciever() {

    }

        @Override
        public void onReceive(Context context, Intent intent){
        Intent recieverIntent = new Intent(context,TimetableNotificationService.class);
        context.startService(recieverIntent);

        }



    }

