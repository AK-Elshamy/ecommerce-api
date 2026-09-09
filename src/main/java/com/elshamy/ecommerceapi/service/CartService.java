package com.elshamy.ecommerceapi.service;


import com.elshamy.ecommerceapi.dto.AddToCartRequest;
import com.elshamy.ecommerceapi.entity.Cart;
import com.elshamy.ecommerceapi.entity.CartItem;
import com.elshamy.ecommerceapi.entity.Product;
import com.elshamy.ecommerceapi.entity.User;
import com.elshamy.ecommerceapi.exception.InsufficientStockException;
import com.elshamy.ecommerceapi.exception.ResourceNotFoundException;
import com.elshamy.ecommerceapi.repository.CartItemRepository;
import com.elshamy.ecommerceapi.repository.CartRepository;
import com.elshamy.ecommerceapi.repository.ProductRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartService {
    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    private Cart createNewCart(User user){
        Cart cart = new Cart();
        cart.setUser(user);
        return cart;
    }
    public void addToCart(AddToCartRequest request){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + request.productId()));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(createNewCart(user)));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(request.productId()))
                .findFirst();

        if(existingItem.isPresent()){
            CartItem item = existingItem.get();
            int newQuantity = item.getQuantity() + request.quantity();
            validateStock(newQuantity, product);
            item.setQuantity(newQuantity);
            cartItemRepository.save(item);
        }else{
            validateStock(request.quantity(), product);
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(request.quantity());
            cartItemRepository.save(newItem);
        }
    }
    private void validateStock(int requestedQuantity, Product product){
        if(requestedQuantity > product.getStockQuantity()){
            throw new InsufficientStockException(
                    "Only " + product.getStockQuantity() + " units available for " + product.getName()
            );
        }
    }



}
