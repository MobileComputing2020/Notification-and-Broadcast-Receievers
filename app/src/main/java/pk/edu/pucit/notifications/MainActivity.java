package pk.edu.pucit.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    final static String default_channel_id = "1234";
    final static String updates_channel_id = "updatesChannel";
    MyReceiver receiver;

    @Override
    protected void onStart() {
        super.onStart();
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("pk.edu.pucit.CUSTOM_INTENT");
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStop() {
        unregisterReceiver(receiver);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String channel_name = "Default Notifications";
            NotificationChannel channel = new NotificationChannel(default_channel_id, channel_name, NotificationManager.IMPORTANCE_HIGH);

            NotificationChannel updates_channel = new NotificationChannel(updates_channel_id, "Latest Updates", NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            manager.createNotificationChannel(updates_channel);
        }

        Notification notification = new NotificationCompat.Builder(this, default_channel_id)
                .setContentText("A new notification")
                .setContentTitle("Notification Title")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .build();


            Notification notification2 = new NotificationCompat.Builder(this, updates_channel_id)
                    .setContentText("Updates available")
                    .setContentTitle("Latest updates")
                    .setSmallIcon(R.drawable.ic_android_black_24dp)
                    .build();

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(123, notification);
        manager.notify(123, notification2);



    }

    public void sendbcast(View view) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SCREEN_OFF);
        sendBroadcast(i);
    }
}
