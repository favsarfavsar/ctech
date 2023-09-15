package com.cookerytech.mapper;

import com.cookerytech.domain.Cart;
import com.cookerytech.dto.response.CartResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-15T03:41:10+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public List<CartResponse> cartToCartResponses(List<Cart> allCarts) {
        if ( allCarts == null ) {
            return null;
        }

        List<CartResponse> list = new ArrayList<CartResponse>( allCarts.size() );
        for ( Cart cart : allCarts ) {
            list.add( cartToCartResponse( cart ) );
        }

        return list;
    }

    protected CartResponse cartToCartResponse(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartResponse cartResponse = new CartResponse();

        cartResponse.setId( cart.getId() );

        return cartResponse;
    }
}
