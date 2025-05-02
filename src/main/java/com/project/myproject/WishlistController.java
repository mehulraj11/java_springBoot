package com.project.myproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<String> addToWishlist(@RequestParam Long userId, @RequestParam Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product Not found"));
        Wishlist wishlist = new Wishlist(user, product);
        wishlistRepository.save(wishlist);
        return ResponseEntity.ok("Added To Wishlist");
    }

    @GetMapping
    public List<WishlistDTO> getAllWishlist() {
        List<Wishlist> wishlistList = wishlistRepository.findAll();
        return wishlistList.stream()
                .map(w -> new WishlistDTO(w.getUser(), w.getProduct()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishlistDTO> getListId(@PathVariable Long id) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        return ResponseEntity.ok(new WishlistDTO(wishlist.getUser(), wishlist.getProduct()));
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updatedWishlist(@PathVariable Long id, @RequestParam Long userId, @RequestParam Long productId) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        wishlist.setUser(user);
        wishlist.setProduct(product);
        wishlistRepository.save(wishlist);

        return ResponseEntity.ok("Wishlist updated successfully");
    }

}
