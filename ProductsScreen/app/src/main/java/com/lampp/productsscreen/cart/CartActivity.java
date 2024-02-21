package com.lampp.productsscreen.cart;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.lampp.productsscreen.R;
import com.lampp.productsscreen.cart.db.CartDB;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView rcvCart;
    private CartDB cartDatabase;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        bindingView();
        setupRecyclerView();
    }

    private void bindingView() {
        rcvCart = findViewById(R.id.rcvCart);
        cartDatabase = Room.databaseBuilder(getApplicationContext(), CartDB.class, "cart_database").build();
    }

    private void setupRecyclerView() {
        new Thread(() -> {
            List<Cart> carts = cartDatabase.cartDAO().getAllCartItems();
            runOnUiThread(() -> {
                cartAdapter = new CartAdapter(carts);
                rcvCart.setAdapter(cartAdapter);
                rcvCart.setLayoutManager(new LinearLayoutManager(this));
            });
        }).start();
    }
}
