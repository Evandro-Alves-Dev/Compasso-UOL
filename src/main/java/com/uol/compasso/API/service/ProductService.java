package com.uol.compasso.API.service;

import com.uol.compasso.API.DTO.productRequest.ProductRequest;
import com.uol.compasso.API.DTO.productResponse.ProductResponse;
import com.uol.compasso.API.entity.Product;
import com.uol.compasso.API.mapper.MapperProductRequestToProduct;
import com.uol.compasso.API.mapper.MapperProductToProductResponse;
import com.uol.compasso.API.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MapperProductToProductResponse mapperProductToProductResponse;

    @Autowired
    private MapperProductRequestToProduct mapperProductRequestToProduct;

    public ProductResponse cadastraProduto(ProductRequest productRequest){

        Product product = mapperProductRequestToProduct.toDto(productRequest);

        Product saved = productRepository.save(product);

        ProductResponse response = mapperProductToProductResponse.toDto(saved);

        return response;
    }
}
