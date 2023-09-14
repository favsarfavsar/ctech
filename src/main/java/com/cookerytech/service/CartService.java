package com.cookerytech.service;

import com.cookerytech.domain.*;
import com.cookerytech.dto.response.CartResponse;
import com.cookerytech.exception.ResourceNotFoundException;
import com.cookerytech.exception.message.ErrorMessage;
import com.cookerytech.mapper.CartMapper;
import com.cookerytech.repository.CartItemRepository;
import com.cookerytech.repository.CartRepository;
import com.cookerytech.repository.ModelRepository;
import com.cookerytech.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final CartMapper cartMapper;

    private final UserService userService;

    private final UserRepository userRepository;

    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, CartMapper cartMapper, UserService userService, UserRepository userRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.userService = userService;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }
    public List<CartResponse> getCart() {

        List<Cart> cartList = cartRepository.findAllCarts();

        Cart newCart = new Cart();
        newCart.setCreateAt(LocalDate.now());
        newCart.setUserId(userService.getCurrentUser());
        cartList.add(newCart);

        if (cartList.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ErrorMessage.NO_DATA_IN_DB_TABLE_MESSAGE, "cartList"));
        }
        Product product = new Product();
        Model model = new Model();

        User user = userService.getCurrentUser();
        for (Cart cart : cartList) {
            if (cart.getUserId().getId().equals(user.getId())) {
                CartResponse cartResponse = new CartResponse();
                cartResponse.setId(cart.getUserId().getId());
                cartResponse.setAmount(1.0);
                cartResponse.setModel(model);
                cartResponse.setProduct(product);
                List<CartResponse> newCartResponse = new ArrayList<>();
                newCartResponse.add(cartResponse);
                return newCartResponse;
            }
        }
        return cartMapper.cartToCartResponses(cartRepository.findAllCarts());
    }
    public CartResponse manageCartItem(Model modelId, Double amount) {

        Cart_Items cartItem = cartItemRepository.findById(modelId.getId()).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION)));

        if (cartItem == null) {
            cartItem = new Cart_Items();
            cartItem.setModelId(modelId);
            cartItem.setAmount(amount);
        } else {
            if (amount == 0) {
                cartItemRepository.delete(cartItem);
            } else {
                cartItem.setAmount(amount);
            }
        }
        cartItem.setUpdatedAt(LocalDateTime.now());
        cartItemRepository.save(cartItem);

        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(cartItem.getId());
        cartResponse.setModel(cartItem.getModelId());
        cartResponse.setProduct(cartItem.getProductId());
        cartResponse.setAmount(cartItem.getAmount());

        return cartResponse;
    }
}
