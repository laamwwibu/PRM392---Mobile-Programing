package com.lampp.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvDictionary;
    private List<Word> data;

    private void bindingView() {
    rcvDictionary = findViewById(R.id.rcvDictionary);

    }
    private void bindingAction() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
        fakeDictionary();
        innitRcvDictionary();
    }

    private void fakeDictionary() {
        data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(new Word("Word " + i, "Definition " + i));
        }
    }

    private void innitRcvDictionary() {
        rcvDictionary.setAdapter(new RcvDictionaryAdapter(data));
        rcvDictionary.setLayoutManager(new LinearLayoutManager(this));
    }
}