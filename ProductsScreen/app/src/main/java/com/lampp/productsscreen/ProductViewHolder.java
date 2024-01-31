package com.lampp.productsscreen;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    ImageView imvProductImage;
    TextView tvName;

    Product product;

    public ProductViewHolder(android.view.View itemView) {
        super(itemView);
        bindingView();
        bindingAction();
    }

    private void bindingView() {
        imvProductImage = itemView.findViewById(R.id.imvProductImage);
        tvName = itemView.findViewById(R.id.tvProductName);
    }

    private void bindingAction() {
        itemView.setOnClickListener(this::viewDetail);
    }

    public void setData(@NonNull Product product) {
        this.product = product;
        imvProductImage.setImageResource(product.getImage());
        tvName.setText(product.getName());
    }

    private void viewDetail(@NonNull View view) {
        Intent i = new Intent(view.getContext(), ProductDetail.class);
        i.putExtra("productName", product.getName());
        i.putExtra("productImage", product.getImage());
        startActivity(view.getContext(), i, null);
    }
}
