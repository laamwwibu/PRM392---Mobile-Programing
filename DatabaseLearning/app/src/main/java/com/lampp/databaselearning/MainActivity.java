package com.lampp.databaselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtWord;
    private EditText edtDefinition;

    private Button btnLoad;

    private Button btnSave;
    private DbContext db;

    private void bindingView() {
        edtWord = findViewById(R.id.edtWord);
        edtDefinition = findViewById(R.id.edtDefinition);
        btnLoad = findViewById(R.id.btnLoad);
        btnSave = findViewById(R.id.btnSave);
        db = new DbContext(this);
    }

    private void bindingAction() {
        btnSave.setOnClickListener(this::onBtnSaveClick);
        btnLoad.setOnClickListener(this::onBtnLoadClick);
    }

    private void onBtnSaveClick(View view) {

        String word = edtWord.getText().toString();
        String definition = edtDefinition.getText().toString();

        if (db.insertNewWord2(word, definition) > 0) {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void onBtnLoadClick(View view) {
        Cursor c = db.getAllWord();

        if (c.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
                String word = c.getString(1);
                String definition = c.getString(2);
                Log.d("lampp.debug", id + "---" + word + "---" + definition);
            } while (c.moveToNext());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
    }
}