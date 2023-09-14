package com.cookerytech.dto.response;

import com.cookerytech.domain.Model;
import com.cookerytech.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

    private Long id;

    private Product product;

    private Model model;

    private Double amount;
}
