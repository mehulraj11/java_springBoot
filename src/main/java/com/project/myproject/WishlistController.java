package com.project.myproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistRepository wishlistRepository;

    @PostMapping
    public ResponseEntity<String> addToWishlist(@RequestBody Wishlist wishlist) {
        wishlistRepository.save(wishlist);
        return ResponseEntity.ok("Product added to wishlist successfully");
    }

//    @GetMapping
//    public List<Wishlist> getAllWishlist() {
//        List<Wishlist> all = wishlistRepository.findAll();
//        return all;
//    }

    @GetMapping
    public ResponseEntity<List<Wishlist>> getAllList() {
        List<Wishlist> wishlist = wishlistRepository.findAll(); // small 'w'
        return ResponseEntity.ok(wishlist);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wishlist>> getWishlistByUser(@PathVariable Long userId) {
        List<Wishlist> wishlist = wishlistRepository.findByUserId(userId);
        return ResponseEntity.ok(wishlist);
    }
}
