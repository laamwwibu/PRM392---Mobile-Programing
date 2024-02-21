package com.lampp.productsscreen.cart;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lampp.productsscreen.R;

public class CartViewHolder extends RecyclerView.ViewHolder {
    ImageView imvProductImage;
    TextView tvProductName;
    TextView tvQuantity;
    Button btnIncrease;
    Button btnDecrease;

    Cart cart;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        bindingView();
        bindingAction();
    }

    private void bindingView() {
        imvProductImage = itemView.findViewById(R.id.imvProductImage);
        tvProductName = itemView.findViewById(R.id.tvProductName);
        tvQuantity = itemView.findViewById(R.id.tvQuantity);
        btnIncrease = itemView.findViewById(R.id.btnIncrease);
        btnDecrease = itemView.findViewById(R.id.btnDecrease);
    }

    private void bindingAction() {
        btnIncrease.setOnClickListener(this::increaseQuantity);
        btnDecrease.setOnClickListener(this::decreaseQuantity);
    }

    private void increaseQuantity(View view) {
        cart.setQuantity(cart.getQuantity() + 1);
        tvQuantity.setText(String.valueOf(cart.getQuantity()));
    }

    private void decreaseQuantity(View view) {
        if (cart.getQuantity() > 0) {
            cart.setQuantity(cart.getQuantity() - 1);
            tvQuantity.setText(String.valueOf(cart.getQuantity()));
        }
    }

    public void setData(@NonNull Cart cart) {
        this.cart = cart;
        imvProductImage.setImageResource(cart.getProductImage());
        tvProductName.setText(cart.getProductName());
        tvQuantity.setText(String.valueOf(cart.getQuantity()));
    }
}
