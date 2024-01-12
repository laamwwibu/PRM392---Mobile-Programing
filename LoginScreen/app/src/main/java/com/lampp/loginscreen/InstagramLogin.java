package com.lampp.loginscreen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InstagramLogin extends AppCompatActivity {

    private Button btnLogin;
    private EditText username;
    private EditText password;

    private void bindingView() {
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    private void bindingAction() {
        btnLogin.setOnClickListener(this::onBtnLoginClick);
        btnLogin.setOnLongClickListener(this::onBtnLoginLongClick);
    }

    private boolean onBtnLoginLongClick(View view) {
        Toast.makeText(this, "Long click", Toast.LENGTH_SHORT).show();
        return false;
    }

    private void onBtnLoginClick(View view) {
        login();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instagram_login); //run tat ca file xml cua 1 activity
        bindingView();
        bindingAction();
    }

    private void login() {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        if(username.equals("admin") && password.equals("123456")) {
            Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login fail", Toast.LENGTH_SHORT).show();
        }
    }
}
