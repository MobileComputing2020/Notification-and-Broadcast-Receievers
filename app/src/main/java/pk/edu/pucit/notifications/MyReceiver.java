package pk.edu.pucit.notifications;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static pk.edu.pucit.notifications.MainActivity.updates_channel_id;

public class MyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Broadcast", Toast.LENGTH_SHORT).show();

        Log.d("received", intent.getAction());

        Notification notification2 = new NotificationCompat.Builder(context, updates_channel_id)
                .setContentText("Updates available\n received action: " + intent.getAction())
                .setContentTitle("Latest updates")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .build();

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(123, notification2);

//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
