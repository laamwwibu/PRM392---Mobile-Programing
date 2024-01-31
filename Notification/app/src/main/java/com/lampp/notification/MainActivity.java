package com.lampp.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify;

    private Button btnSave;
    private EditText edtData;
    private static final String CHANNEL_ID = "SE1715_CHANNEL";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private void bindingView() {
        btnNotify = findViewById(R.id.btnNotify);
        edtData = findViewById(R.id.edtData);
        btnSave = findViewById(R.id.btnSave);
        sharedPreferences = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void bindingAction() {
        btnNotify.setOnClickListener(this::notify);
        btnSave.setOnClickListener(this::save);
    }

    private void save(View view) {
        String message = edtData.getText().toString();
        editor.putString("message", message);

        //both are the same, use to save the preference
//        editor.apply(); // if we dont need to know the result
//        editor.commit();// if we need to know the result
        Toast.makeText(this, editor.commit() ? "Saved" : "Fail", Toast.LENGTH_SHORT).show();

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
        onOpenActivity();
    }

    private void onReceiveIntent() {
        Intent i = getIntent();
        String message = i.getStringExtra("message");
        edtData.setText(message);
    }

    private void onOpenActivity() {
        String message = sharedPreferences.getString("message", null);
        if (message != null) {
            edtData.setText(message);
        }
    }
}