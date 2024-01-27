package com.lampp.productsscreen;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    ImageView imvProductImage;
    TextView tvName;

    private void bindingView() {
        imvProductImage = itemView.findViewById(R.id.imvProductImage);
        tvName = itemView.findViewById(R.id.tvProductName);
    }

    private void bindingAction() {
    }

    public void setData(Product product) {
        imvProductImage.setImageResource(product.getImage());
        tvName.setText(product.getName());
    }

    public ProductViewHolder(android.view.View itemView) {
        super(itemView);
        bindingView();
    }

}
