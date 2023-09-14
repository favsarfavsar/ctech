package com.cookerytech.controller;

import com.cookerytech.domain.Cart;
import com.cookerytech.domain.User;
import com.cookerytech.dto.request.CartItemRequest;
import com.cookerytech.dto.response.CartResponse;
import com.cookerytech.service.CartService;
import com.cookerytech.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final UserService userService;

    private final CartService cartService;

    public CartController(UserService userService,@Lazy CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/auth")
    public ResponseEntity<List<CartResponse>> getUsersCart(){

        List<CartResponse> cart = cartService.getCart();

        return ResponseEntity.ok(cart);
    }

    @PostMapping("/auth")
    public ResponseEntity<CartResponse> manageCartItem(@RequestBody CartItemRequest cartItemRequest) {
        CartResponse cartResponse = cartService.manageCartItem(
                cartItemRequest.getModelId(),
                cartItemRequest.getAmount()
        );

        return ResponseEntity.ok(cartResponse);
    }

}
