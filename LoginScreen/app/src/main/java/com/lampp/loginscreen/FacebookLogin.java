package com.lampp.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FacebookLogin extends AppCompatActivity {

    private Button btnInstagramLogin;
    private Button btnLogin;
    private EditText username;
    private EditText password;

    private Spinner spinner;

    private void bindingView() {
        btnInstagramLogin = (Button) findViewById(R.id.buttonInstagramLogin);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        spinner = findViewById(R.id.languageSpinner);
    }

    private void bindingAction() {
        btnInstagramLogin.setOnClickListener(this::onBtnInstagramLoginClick);
        btnLogin.setOnClickListener(this::onBtnLoginClick);
    }

    private void onBtnInstagramLoginClick(View view) {
        Intent intent = new Intent(getApplicationContext(), InstagramLogin.class);
        startActivity(intent);
    }

    private void setSpinnerData() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.facebook_language,
                R.layout.spinner_item_layout
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void onBtnLoginClick(View view) {
        login();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook_login);
        bindingView();
        bindingAction();

        setSpinnerData();
    }
}