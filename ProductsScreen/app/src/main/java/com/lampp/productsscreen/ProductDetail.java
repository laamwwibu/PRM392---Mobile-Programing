package com.lampp.productsscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetail extends AppCompatActivity {
    private ImageView imvProductImage;
    private TextView tvProductName;

    private void bindingView() {
        imvProductImage = findViewById(R.id.productDetailImage);
        tvProductName = findViewById(R.id.productDetailName);
    }

    private void onReceiveIntent() {
        Intent i = getIntent();
        String name = i.getStringExtra("productName");
        int image = i.getIntExtra("productImage", 0);
        tvProductName.setText(name);
        imvProductImage.setImageResource(image);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        bindingView();
        onReceiveIntent();
    }
}
