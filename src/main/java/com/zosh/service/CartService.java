package com.zosh.service;

import com.zosh.modal.Cart;
import com.zosh.modal.CartItem;
import com.zosh.modal.Product;
import com.zosh.modal.User;

public interface CartService {

    public CartItem addCartItem(User user, Product product, String size, int quantity);

    public Cart findUserCart(User user);
}
