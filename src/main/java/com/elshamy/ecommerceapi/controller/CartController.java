package com.elshamy.ecommerceapi.controller;

import com.elshamy.ecommerceapi.dto.AddToCartRequest;
import com.elshamy.ecommerceapi.dto.CartItemDTO;
import com.elshamy.ecommerceapi.dto.CartResponseDTO;
import com.elshamy.ecommerceapi.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(
            @Valid @RequestBody AddToCartRequest request) {

        cartService.addToCart(request);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/get")
    public ResponseEntity<CartResponseDTO> getCart(){
        return ResponseEntity.ok(cartService.getCart());
    }


    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long productId){
        cartService.removeFromCart(productId);
        return ResponseEntity.ok().build();
    }

}