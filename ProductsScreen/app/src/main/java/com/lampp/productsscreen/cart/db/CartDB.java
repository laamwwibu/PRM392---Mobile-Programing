package com.lampp.productsscreen.cart.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.lampp.productsscreen.cart.Cart;
import com.lampp.productsscreen.cart.dao.CartDAO;

@Database(entities = {Cart.class}, version = 1)
public abstract class CartDB extends RoomDatabase {
    public abstract CartDAO cartDAO();
}
