package com.fpt.khangpq.se1715njlifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    private EditText edtShowData;
    private Button btnSave;
    private void bindingView(){
        edtShowData = findViewById(R.id.edtShowData);
        btnSave = findViewById(R.id.btnSave);
    }
    private void bindingAction(){
        btnSave.setOnClickListener(this::onBtnSaveClick);
    }

    private void onBtnSaveClick(View view) {
        Intent i = new Intent();
        String data = edtShowData.getText().toString();
        i.putExtra("result",data);
//        startActivity(i);
        setResult(200,i);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d("khangpqdebug","onCreate2");
        bindingView();
        bindingAction();
        onReceiveIntent();
    }

    private void onReceiveIntent() {
        Intent i = getIntent();
        String data = i.getStringExtra("data");
        int age = i.getIntExtra("age",-1);
        edtShowData.setText(data+" --- "+age);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("khangpqdebug","onStart2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("khangpqdebug","onResume2");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("khangpqdebug","onPause2");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("khangpqdebug","onStop2");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("khangpqdebug","onRestart2");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("khangpqdebug","onDestroy2");

    }
}