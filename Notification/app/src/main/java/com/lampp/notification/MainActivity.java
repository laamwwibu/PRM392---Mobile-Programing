package com.lampp.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify;
    private EditText edtData;
    private static final String CHANNEL_ID = "SE1715_CHANNEL";

    private void bindingView() {
        btnNotify = findViewById(R.id.btnNotify);
        edtData = findViewById(R.id.edtData);
    }

    private void bindingAction() {
        btnNotify.setOnClickListener(this::notify);
    }

    private void notify(View view) {
        String message = edtData.getText().toString();
        sendNotifyMessage(message);
    }

    private void sendNotifyMessage(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("message", message);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_MUTABLE);
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground) //required
                .setContentTitle("Notification")
                .setContentText(message)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setStyle(new Notification.BigTextStyle().bigText(message))
                .setAutoCancel(true); // close notification when user click on it

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "SE1715 DEMO", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        Notification notification = builder.build();
        manager.notify(10, notification);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
        onReceiveIntent();
    }

    private void onReceiveIntent() {
        Intent i = getIntent();
        String message = i.getStringExtra("message");
        edtData.setText(message);
    }
}