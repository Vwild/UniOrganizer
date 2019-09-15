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

//Code By: Vincent Eichenseher
//BroadcastReceiver der über einen Pendingintent des Alarmmanagers aufgerufen wird und anschließend eine neue Notification mit den übergegebenen daten erstellt

public class TimetableNotificationReciever extends BroadcastReceiver {

    //Konstanten zur Erstellung der Notification und Schlüsselwerte um die Daten vom Intent zu holen
    private static final String LECTURE_NAME = "lecture_name";
    private static final String LECTURE_ROOM = "lecture_room";
    private static final String REMINDER_CHANNEL_ID = "reminder_channel";
    private int REMINDER_ID = 0;



    public TimetableNotificationReciever() {

    }

        @Override
        public void onReceive(Context context, Intent intent){
        //ReminderID erhöht sich immer um 1 damit jede Notification eine einzigartige ID hatt.
        this.REMINDER_ID = this.REMINDER_ID+1;
        //holen der vom Intent übergegebenen Daten
        Bundle extras = intent.getExtras();
        String name = extras.getString(LECTURE_NAME);
        String room = extras.getString(LECTURE_ROOM);
        Uri reminderSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager ReminderManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Intent um durch anklicken der notification zur Mainactivity zu gelangen
        Intent recieverIntent = new Intent(context,MainActivity.class);
        recieverIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,this.REMINDER_ID,recieverIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        //erstellen der Notification mit den übergegebenen Daten
        NotificationCompat.Builder Reminder = new NotificationCompat.Builder(context,REMINDER_CHANNEL_ID);
        Reminder.setContentIntent(pendingIntent);
        Reminder.setSmallIcon(R.drawable.ic_timetable_notification_icon);
        Reminder.setContentTitle(name +" begins in 15min");
        Reminder.setContentText("location:"+room);
        Reminder.setAutoCancel(true);
        Reminder.setPriority(NotificationCompat.PRIORITY_HIGH);
        Reminder.setSound(reminderSound);

        ReminderManager.notify(this.REMINDER_ID,Reminder.build());

        }





    }

