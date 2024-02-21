package com.lampp.productsscreen.products.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.lampp.productsscreen.R;
import com.lampp.productsscreen.products.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsDBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "products_database";
    private static final String TB_PRODUCTS = "products";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_IMAGE = "image";

    public ProductsDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TB_PRODUCTS + "(" + COL_ID + " INTEGER PRIMARY KEY," + COL_NAME + " TEXT," + COL_IMAGE + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
        //add default product
        addDefaultProduct(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addDefaultProduct(SQLiteDatabase db) {
        List<Product> productList = fakeProduct();

        for (Product product : productList) {
            addProduct(product, db);
        }
    }

    public void addProduct(Product product, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, product.getName());
        values.put(COL_IMAGE, product.getImage());
        // Inserting Row
        db.insert(TB_PRODUCTS, null, values);
    }

    private List<Product> fakeProduct() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Pharmacy", com.lampp.productsscreen.R.drawable.icons8_pharma_250));
        productList.add(new Product("Registry", R.drawable.icons8_present_250));
        productList.add(new Product("Cartwheel", R.drawable.icons8_cart_240));
        productList.add(new Product("Clothing", R.drawable.icons8_fireman_coat_250));
        productList.add(new Product("Shoes", R.drawable.icons8_shoes_256));
        productList.add(new Product("Accessories", R.drawable.icons8_handbag_250));
        return productList;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TB_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setName(cursor.getString(1));
                product.setImage(cursor.getInt(2));
                // Adding product to list
                productList.add(product);
            } while (cursor.moveToNext());
        }

        // close cursor
        cursor.close();

        // return product list
        return productList;
    }
}
