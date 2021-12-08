package com.uol.compasso.API.mapper;

import com.uol.compasso.API.DTO.productRequest.ProductRequest;
import com.uol.compasso.API.entity.Product;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MapperProductRequestToProduct {
    Product toDto(ProductRequest productRequest);
}
