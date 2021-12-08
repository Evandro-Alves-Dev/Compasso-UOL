package com.uol.compasso.API.mapper;

import com.uol.compasso.API.DTO.productResponse.ProductResponse;
import com.uol.compasso.API.entity.Product;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MapperProductToProductResponse {
    ProductResponse toDto(Product product);
}
