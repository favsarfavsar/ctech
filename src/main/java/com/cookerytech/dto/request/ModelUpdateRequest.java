package com.cookerytech.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ModelUpdateRequest {

    @NotBlank
    @Size(min = 5, max = 50)
    private String title;
    @NotBlank
    private String sku;
    @NotBlank
    private Integer stockAmount;
    @NotBlank
    private Integer inBoxQuantity;
    @NotBlank
    private Integer seq;
    @NotBlank
    private Integer imageId;
    @NotBlank
    private Double buyingPrice;
    @NotBlank
    private Double taxRate;
    @NotBlank
    private Boolean isActive;
    @NotBlank
    private String currencyCode;
    @NotBlank
    private  Long productId;

    //private Boolean builtIn; // ?? Update edilebilecek mi?

}
