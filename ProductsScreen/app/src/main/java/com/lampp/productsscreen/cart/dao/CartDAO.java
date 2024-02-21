package com.lampp.productsscreen.cart.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lampp.productsscreen.cart.Cart;

import java.util.List;

@Dao
public interface CartDAO {
    @Insert
    void insert(Cart cart);

    @Update
    void update(Cart cart);

    @Query("DELETE FROM cart_table WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM cart_table")
    List<Cart> getAllCartItems();

    @Query("SELECT * FROM cart_table WHERE productName = :productName LIMIT 1")
    Cart getCartItemByProductName(String productName);
}
