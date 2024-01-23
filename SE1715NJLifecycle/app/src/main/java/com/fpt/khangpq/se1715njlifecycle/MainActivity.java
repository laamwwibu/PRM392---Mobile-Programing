package com.fpt.khangpq.se1715njlifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnLoad;
    private Button btnDial;
    private Button btnOpen;
    private EditText edtData;

    private void bindingView() {
        btnLoad = findViewById(R.id.btnLoad);
        btnDial = findViewById(R.id.btnDial);
        btnOpen = findViewById(R.id.btnOpen);
        edtData = findViewById(R.id.edtData);
    }

    private void bindingAction() {
        btnLoad.setOnClickListener(this::onBtnLoadClick);
        btnDial.setOnClickListener(this::onBtnDialClick);
        btnOpen.setOnClickListener(this::onBtnOpenClick);
    }

    private final int REQUEST_CODE_OPEN_ACT2 = 2;

    private void onBtnOpenClick(View view) {
        Intent i = new Intent(this, MainActivity2.class);
        String data = edtData.getText().toString();
        i.putExtra("data", data);
        i.putExtra("age", 18);
//        startActivity(i);
        startActivityForResult(i, REQUEST_CODE_OPEN_ACT2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //make sure that the implementation of onActivityResult in the superclass still gets executed
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_OPEN_ACT2:
                if (resultCode == 200) {
                    String result = data.getStringExtra("result");
                    edtData.setText(result);
                } else {
                    Toast.makeText(this, "LOI ROI " + resultCode, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void onBtnDialClick(View view) {
        String phone = edtData.getText().toString();
        Uri uri = Uri.parse("tel:" + phone);
        Intent it = new Intent(Intent.ACTION_CALL, uri);
        startActivity(it);

    }

    private void onBtnLoadClick(View view) {
        String url = edtData.getText().toString();
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
//        onReceiveIntent();
    }

    private void onReceiveIntent() {
        Intent i = getIntent();
        String result = i.getStringExtra("result");
        edtData.setText(result);
    }
}