package com.lampp.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {
    private TextView tvUserLoggedIn;

    private Button btnClose;

    private void bindingView() {
        tvUserLoggedIn = findViewById(R.id.tvUserLoggedIn);
        btnClose = findViewById(R.id.btnClose);
    }

    private void bindingAction() {
        btnClose.setOnClickListener(this::onBtnCloseClick);
    }

    private void onReceiveIntent() {
        Intent i = getIntent();
        String username = i.getStringExtra("username");
        tvUserLoggedIn.setText(username);
    }

    private void onBtnCloseClick(View view) {
        finishAffinity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        bindingView();
        bindingAction();
        onReceiveIntent();
    }

    @Override
    protected void onStop() {
        super.onStop();
        clearUsername();
    }

    private void clearUsername() {
        tvUserLoggedIn.setText("");
    }
}
