package com.cookerytech.dto.response;

import com.cookerytech.domain.OfferItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferCreateResponse {

    private Long id;
    private String code;
    private String status;
    List<OfferItem> items = new ArrayList<>();
}
