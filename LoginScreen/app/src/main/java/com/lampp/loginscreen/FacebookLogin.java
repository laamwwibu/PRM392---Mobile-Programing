package com.lampp.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class FacebookLogin extends AppCompatActivity {

    private Button btnInstagramLogin;

    private Spinner spinner;

    private void bindingView() {
        btnInstagramLogin = (Button) findViewById(R.id.buttonInstagramLogin);
        spinner = findViewById(R.id.languageSpinner);

    }

    private void bindingAction() {
        btnInstagramLogin.setOnClickListener(this::onBtnInstagramLoginClick);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook_login);
        bindingView();
        bindingAction();

        setSpinnerData();
    }
}