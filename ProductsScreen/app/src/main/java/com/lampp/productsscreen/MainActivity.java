package com.lampp.productsscreen;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvProduct;
    private List<Product> data;
    private Button btnContextMenu;

    private static final int NUMBER_OF_COLUMNS = 3;

    private void bindingView() {
        rcvProduct = findViewById(R.id.rcvProduct);
        btnContextMenu = findViewById(R.id.btnContextMenu);
    }

    private void bindingAction() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
        fakeProduct();
        initRcvProduct();
        registerForContextMenu(btnContextMenu);
    }

    private void initRcvProduct() {
        rcvProduct.setAdapter(new RcvProductAdapter(data));
        rcvProduct.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));
    }

    private void fakeProduct() {
        data = new ArrayList<>();
        data.add(new Product("Pharmacy", R.drawable.icons8_pharma_250));
        data.add(new Product("Registry", R.drawable.icons8_present_250));
        data.add(new Product("Cartwheel", R.drawable.icons8_cart_240));
        data.add(new Product("Clothing", R.drawable.icons8_fireman_coat_250));
        data.add(new Product("Shoes", R.drawable.icons8_shoes_256));
        data.add(new Product("Accessories", R.drawable.icons8_handbag_250));
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
        if(itemId == R.id.optSetting) {
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
        }
        if(itemId == R.id.optProfile) {
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
        }
        if(itemId == R.id.optLogout) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}