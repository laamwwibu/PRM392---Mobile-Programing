package com.lampp.phoneapppermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btnCall;
    private EditText edtPhoneNumber;

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    private void bindingView() {
        btnCall = findViewById(R.id.btnCall);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
    }

    private void bindingAction() {
        btnCall.setOnClickListener(this::getPhonePermissionOrShowDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
    }

    private void callPhoneNumber() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + edtPhoneNumber.getText().toString()));
        startActivity(intent);
    }

    private void getPhonePermissionOrShowDialog(View view) {
        if (isPhonePermission()) {
            // Permission not granted, show initial dialog
            showInitialDialog();
        } else {
            // Permission already granted, make the call
            callPhoneNumber();
        }
    }

    private boolean isPhonePermission() {
        return (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED);
    }

    private void showInitialDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Permission Needed")
                .setMessage("This app needs to make phone calls.")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Request permission
                    requestPhonePermission();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void requestPhonePermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CALL_PHONE},
                MY_PERMISSIONS_REQUEST_CALL_PHONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, make the call
                callPhoneNumber();
            } else {
                // Permission denied, show permission denied dialog
                showPermissionDeniedDialog();
            }
        }
    }

    private void showPermissionDeniedDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Permission Denied")
                .setMessage("You have denied the permission to make phone calls.")
                .setPositiveButton("OK", null)
                .show();
    }
}