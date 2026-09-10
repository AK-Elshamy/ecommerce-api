package com.elshamy.ecommerceapi.service;

import com.elshamy.ecommerceapi.dto.OrderItemDTO;
import com.elshamy.ecommerceapi.dto.OrderResponseDTO;
import com.elshamy.ecommerceapi.entity.*;
import com.elshamy.ecommerceapi.exception.InsufficientStockException;
import com.elshamy.ecommerceapi.exception.ResourceNotFoundException;
import com.elshamy.ecommerceapi.repository.CartItemRepository;
import com.elshamy.ecommerceapi.repository.CartRepository;
import com.elshamy.ecommerceapi.repository.OrderItemRepository;
import com.elshamy.ecommerceapi.repository.OrderRepository;
import com.elshamy.ecommerceapi.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository,
            CartRepository cartRepository,
            CartItemRepository cartItemRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    private OrderItemDTO toOrderItemDTO(CartItem cartItem) {

        BigDecimal price = cartItem.getProduct().getPrice();

        BigDecimal subtotal = price.multiply(
                BigDecimal.valueOf(cartItem.getQuantity())
        );

        return new OrderItemDTO(
                cartItem.getProduct().getName(),
                cartItem.getQuantity(),
                price,
                subtotal
        );
    }

    private OrderItem cartItemToOrderItem(
            CartItem cartItem,
            Order order
    ) {

        OrderItem orderItem = new OrderItem();

        orderItem.setProduct(cartItem.getProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setPriceAtPurchase(
                cartItem.getProduct().getPrice()
        );
        orderItem.setOrder(order);

        return orderItem;
    }

    @Transactional
    public OrderResponseDTO checkout() {


        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();


        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No cart found")
                );


        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException(
                    "Cannot checkout an empty cart"
            );
        }


        checkStock(cart);


        Order order = new Order();

        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        Order savedOrder = orderRepository.save(order);


        List<OrderItem> orderItemList = cart.getItems()
                .stream()
                .map(item -> cartItemToOrderItem(item, savedOrder))
                .toList();


        orderItemRepository.saveAll(orderItemList);

        savedOrder.setItems(orderItemList);


        for (CartItem cartItem : cart.getItems()) {

            Product product = cartItem.getProduct();

            int newStock =
                    product.getStockQuantity()
                            - cartItem.getQuantity();

            product.setStockQuantity(newStock);

            productRepository.save(product);
        }


        List<OrderItemDTO> items = cart.getItems()
                .stream()
                .map(this::toOrderItemDTO)
                .toList();


        BigDecimal totalAmount = items.stream()
                .map(OrderItemDTO::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();


        return new OrderResponseDTO(
                order.getId(),
                order.getOrderDate(),
                order.getStatus().name(),
                items,
                totalAmount
        );
    }

    private void checkStock(Cart cart) {

        for (CartItem item : cart.getItems()) {

            int requestedQuantity = item.getQuantity();

            Product product = item.getProduct();

            if (requestedQuantity > product.getStockQuantity()) {

                throw new InsufficientStockException(
                        "Not enough stock for "
                                + product.getName()
                                + ". Available: "
                                + product.getStockQuantity()
                                + ", requested: "
                                + requestedQuantity
                );
            }
        }
    }
}