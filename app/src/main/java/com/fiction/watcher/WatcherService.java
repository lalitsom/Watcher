package com.fiction.watcher;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.io.FileOutputStream;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class WatcherService extends NotificationListenerService {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(this.getClass().toString(),"Created Service");
    }


    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        Log.e(this.getClass().toString(),"Listener Connected");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(this.getClass().toString(),"onstart commmand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.e(this.getClass().toString(),"Notifications available");


        try{
            Bundle extras = sbn.getNotification().extras;
            String N_text = extras.getCharSequence(Notification.EXTRA_TEXT).toString();
            String N_title = extras.getCharSequence(Notification.EXTRA_TITLE).toString();
            Log.e(this.getClass().toString(),   "msg : " + N_text);
            Log.e(this.getClass().toString(),   "msg : " + N_title);
            String packagename = sbn.getPackageName();
            if(sbn.getNotification().tickerText!=null){
                packagename = packagename + " : " + sbn.getNotification().tickerText.toString();
            }
            Log.e(this.getClass().toString(),   "ticker : " + packagename);
            write_to_file(packagename + ":\n" + N_title+": "+ N_text);

        }catch (Exception e){
            Log.e(this.getClass().toString(),"on_notifi..posted " + e.getMessage());
        }

        super.onNotificationPosted(sbn);
    }

public void write_to_file(String msg){
    String filename = "Watcher_logs";
    FileOutputStream outputStream;

    try {
        outputStream = openFileOutput(filename, Context.MODE_APPEND | Context.MODE_PRIVATE);
        outputStream.write(msg.getBytes());
        outputStream.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.e(this.getClass().toString(),"notification removed");


        try{

        }catch (Exception e){
            Log.e(this.getClass().toString(),"onnotifi..posted " + e.getMessage());
        }

        super.onNotificationRemoved(sbn);
    }
}
