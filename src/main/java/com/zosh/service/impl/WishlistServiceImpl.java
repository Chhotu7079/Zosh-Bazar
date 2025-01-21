package com.zosh.service.impl;

import org.springframework.stereotype.Service;

import com.zosh.modal.Product;
import com.zosh.modal.User;
import com.zosh.modal.Wishlist;
import com.zosh.repository.WishlistRepository;
import com.zosh.service.WishlistService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService{

    private final WishlistRepository wishlistRepository;


    @Override
    public Wishlist createWishlist(User user) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlistByUserId(User user) {
        Wishlist wishlist =  wishlistRepository.findByUserId(user.getId());
        if (wishlist == null) {
            wishlist = createWishlist(user);
        }
        return wishlist;
    }

    @Override
    public Wishlist addProductToWishlist(User user, Product product) {
        Wishlist wishlist = getWishlistByUserId(user);
        if (wishlist.getProducts().contains(product)) {
            wishlist.getProducts().remove(product);
        }
        else{
            wishlist.getProducts().add(product);
        }
        return wishlistRepository.save(wishlist);
    }

}
