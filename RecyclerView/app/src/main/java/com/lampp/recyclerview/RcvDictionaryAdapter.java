package com.lampp.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RcvDictionaryAdapter extends RecyclerView.Adapter<WordViewHolder> {
    private List<Word> data;

    public RcvDictionaryAdapter(List<Word> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutinflater.inflate(R.layout.wird_items_layout, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = data.get(position);
        holder.setData(word);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
