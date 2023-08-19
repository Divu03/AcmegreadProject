package com.example.shopping.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopping.Product;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends ViewModel {

    private MutableLiveData<List<Product>> cartItems = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Product>> getCartItems() {
        return cartItems;
    }

    public void addToCart(Product product) {
        List<Product> currentItems = cartItems.getValue();
        if (currentItems != null && !product.isInCart()) {
            currentItems.add(product);
            cartItems.setValue(currentItems);
            product.setInCart(true);
        }
    }

    public void clearCart() {
        cartItems.setValue(new ArrayList<>());
    }
}
