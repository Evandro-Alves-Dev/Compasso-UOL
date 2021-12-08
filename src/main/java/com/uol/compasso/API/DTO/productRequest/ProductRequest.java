package com.uol.compasso.API.DTO.productRequest;

import lombok.Builder;
import lombok.Data;

public class ProductRequest {

//    @NotBlank
    private String name;

//    @NotBlank
    private String description;

    private Float price;
}
