package com.example.uniorganizer;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

public class TimetableNotificationReciever extends BroadcastReceiver {

    private static final String LECTURE_NAME = "lecture_name";
    private static final String LECTURE_ROOM = "lecture_room";
    private int REMINDER_ID = 0;
    private static final String REMINDER_CHANNEL_ID = "reminder_channel";



    public TimetableNotificationReciever() {

    }

        @Override
        public void onReceive(Context context, Intent intent){
        this.REMINDER_ID = this.REMINDER_ID+1;
        Bundle extras = intent.getExtras();
        String name = extras.getString(LECTURE_NAME);
        String room = extras.getString(LECTURE_ROOM);

        NotificationManager ReminderManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent recieverIntent = new Intent(context,MainActivity.class);
        recieverIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Uri reminderSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,this.REMINDER_ID,recieverIntent,PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder Reminder = new NotificationCompat.Builder(context,REMINDER_CHANNEL_ID);
        Reminder.setContentIntent(pendingIntent);
        Reminder.setSmallIcon(R.drawable.ic_timetable_notification_icon);
        Reminder.setContentTitle(name +" beginns in 15min");
        Reminder.setContentText("location:"+room);
        Reminder.setAutoCancel(true);
        Reminder.setPriority(NotificationCompat.PRIORITY_HIGH);
        Reminder.setSound(reminderSound);

        ReminderManager.notify(this.REMINDER_ID,Reminder.build());

        }





    }

