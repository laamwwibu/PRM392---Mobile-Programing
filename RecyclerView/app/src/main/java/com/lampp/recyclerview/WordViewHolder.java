package com.lampp.recyclerview;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordViewHolder extends RecyclerView.ViewHolder {
    private TextView tvWord;
    private TextView tvDefinition;

    private void bindingView() {
        tvWord = itemView.findViewById(R.id.tvWord);
        tvDefinition = itemView.findViewById(R.id.tvDefinition);
    }

    private void bindingAction() {
        tvWord.setOnClickListener(this::onTvWordClick);
        tvWord.setOnClickListener(this::onTvDefinitionClick);
        itemView.setOnClickListener(this::onItemClick);
    }

    private void onItemClick(View view) {
        String data = tvWord.getText().toString();
        Toast.makeText(itemView.getContext(), data, Toast.LENGTH_SHORT).show();
    }

    private void onTvDefinitionClick(View view) {

    }

    private void onTvWordClick(View view) {
        String data = tvWord.getText().toString();
        Toast.makeText(itemView.getContext(), data, Toast.LENGTH_SHORT).show();
    }

    public WordViewHolder(@NonNull android.view.View itemView) {
        super(itemView);
        bindingView();
        bindingAction();
    }

    public void setData(Word word) {
        tvWord.setText(word.getWord());
        tvDefinition.setText(word.getDefinition());
    }
}
