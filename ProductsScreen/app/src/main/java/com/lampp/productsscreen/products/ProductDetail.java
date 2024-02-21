package com.lampp.productsscreen.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.lampp.productsscreen.R;
import com.lampp.productsscreen.cart.Cart;
import com.lampp.productsscreen.cart.db.CartDB;

public class ProductDetail extends AppCompatActivity {
    private ImageView imvProductImage;
    private TextView tvProductName;
    private Button btnAddToCart;
    private EditText edtAmount;
    private CartDB cartDB;

    private void bindingView() {
        imvProductImage = findViewById(R.id.productDetailImage);
        tvProductName = findViewById(R.id.productDetailName);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        edtAmount = findViewById(R.id.edtAmount);
        cartDB = Room.databaseBuilder(getApplicationContext(), CartDB.class, "cart_database").build();
    }

    private void bindingAction() {
        btnAddToCart.setOnClickListener(this::onBtnAddToCartClick);
    }

    private void onBtnAddToCartClick(View view) {
        new Thread(() -> {
            Cart existingCart = cartDB.cartDAO().getCartItemByProductName(getIntentProductName());
            if(existingCart == null) {
                Cart cart = new Cart();
                cart.setProductName(getIntentProductName());
                cart.setProductImage(getIntentProductImage());
                cart.setQuantity(Integer.parseInt(edtAmount.getText().toString()));
                cartDB.cartDAO().insert(cart);
            } else {
                existingCart.setQuantity(existingCart.getQuantity() + Integer.parseInt(edtAmount.getText().toString()));
                cartDB.cartDAO().update(existingCart);
            }

        }).start();
    }

    private String getIntentProductName() {
        Intent i = getIntent();
        return i.getStringExtra("productName");
    }

    private int getIntentProductImage() {
        Intent i = getIntent();
        return i.getIntExtra("productImage", 0);
    }

    private void onReceiveIntent() {
        Intent i = getIntent();
        String name = getIntentProductName();
        int image = getIntentProductImage();
        tvProductName.setText(name);
        imvProductImage.setImageResource(image);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        bindingView();
        bindingAction();
        onReceiveIntent();
    }
}
