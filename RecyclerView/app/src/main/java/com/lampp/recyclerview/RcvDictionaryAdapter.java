package com.lampp.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DictionaryRcvAdapter extends RecyclerView.Adapter<DictionaryViewHolder> {
    private List<Word> data;

    public DictionaryRcvAdapter(List<Word> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public DictionaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        DictionaryViewHolder wordViewHolder = new DictionaryViewHolder(itemView);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
