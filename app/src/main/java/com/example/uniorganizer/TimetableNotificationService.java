package com.example.uniorganizer;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class TimetableNotificationService extends IntentService {
    private static final int REMINDER_ID = 1;
    private static final String REMINDER_CHANNEL_ID = "a1";

    public TimetableNotificationService(){
        super("TimetableNotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        Intent NotificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,NotificationIntent,0);


        NotificationCompat.Builder Reminder = new NotificationCompat.Builder(this,REMINDER_CHANNEL_ID);
        Reminder.setSmallIcon(R.drawable.ic_timetable_notification_icon);
        Reminder.setContentTitle("");
        Reminder.setContentText("");
        Reminder.setPriority(NotificationCompat.PRIORITY_HIGH);
        Reminder.setContentIntent(pendingIntent);

        NotificationManager ReminderManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
        ReminderManager.notify(REMINDER_ID,Reminder.build());

    }

}
