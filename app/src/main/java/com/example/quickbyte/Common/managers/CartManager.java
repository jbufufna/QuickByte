package com.example.quickbyte.Common.managers;

import com.example.quickbyte.API.DTO.MenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.*;
import androidx.core.util.Pair;

public class CartManager {

    private static CartManager instance;
    private final Map<MenuItem, Integer> cartItems = new HashMap<>();

    private CartManager() {}

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void updateItemQuantity(MenuItem item, int quantityChange) {
        int currentQuantity = cartItems.getOrDefault(item, 0);
        int newQuantity = currentQuantity + quantityChange;

        if (newQuantity > 0) {
            cartItems.put(item, newQuantity);
        } else {
            cartItems.remove(item);
        }
    }

    public int getItemQuantity(MenuItem item) {
        return cartItems.getOrDefault(item, 0);
    }

    public Map<MenuItem, Integer> getCartItems() {
        return cartItems;
    }

}
