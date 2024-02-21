package com.lampp.productsscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lampp.productsscreen.cart.CartActivity;
import com.lampp.productsscreen.products.dbhelper.ProductsDBHelper;
import com.lampp.productsscreen.products.Product;
import com.lampp.productsscreen.products.RcvProductAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvProduct;
    private List<Product> data;

    private ProductsDBHelper productsDBHelper;

    private static final int NUMBER_OF_COLUMNS = 3;

    private void bindingView() {
        rcvProduct = findViewById(R.id.rcvProduct);
        productsDBHelper = new ProductsDBHelper(this);
    }

    private void bindingAction() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();

        setProductsData();
        initRcvProduct();
        setActionBar();

    }

    public void setProductsData() {
        data = productsDBHelper.getAllProducts();
    }

    private void initRcvProduct() {
        rcvProduct.setAdapter(new RcvProductAdapter(data));
        rcvProduct.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_opton_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main_opton_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "Context menu" + item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.optSetting) {
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
        }
        if (itemId == R.id.optProfile) {
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
        }
        if (itemId == R.id.optLogout) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }
        if (itemId == R.id.optCart) {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Shop");
        }
    }
}