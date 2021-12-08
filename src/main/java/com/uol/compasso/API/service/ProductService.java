package com.uol.compasso.API.service;

import com.uol.compasso.API.DTO.productRequest.ProductRequest;
import com.uol.compasso.API.DTO.productResponse.ProductResponse;
import com.uol.compasso.API.entity.Product;
import com.uol.compasso.API.exceptions.DescriptionException;
import com.uol.compasso.API.exceptions.NameException;
import com.uol.compasso.API.exceptions.PriceException;
import com.uol.compasso.API.mapper.MapperProductRequestToProduct;
import com.uol.compasso.API.mapper.MapperProductToProductResponse;
import com.uol.compasso.API.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MapperProductToProductResponse mapperProductToProductResponse;

    @Autowired
    private MapperProductRequestToProduct mapperProductRequestToProduct;

    public ProductResponse cadastraProduto(ProductRequest productRequest){
        int priceChecked = productRequest.getPrice().compareTo(new BigDecimal("0.1"));

        if (Objects.isNull(productRequest.getName()) || productRequest.getName().isEmpty()) {
            throw new NameException("Nome não pode ser nulo");
        }else if (Objects.isNull(productRequest.getDescription()) || productRequest.getDescription().isEmpty()){
            throw new DescriptionException("Descrição não pode estar nula");
        }else if (Objects.isNull(productRequest.getPrice())){
            throw new PriceException("Preço não pode ser nulo");
        }else if (priceChecked < 0){
            throw new PriceException("Preço deve conter valor positivo");
        }else {

            Product product = mapperProductRequestToProduct.toDto(productRequest);

            Product saved = productRepository.save(product);

            ProductResponse response = mapperProductToProductResponse.toDto(saved);

            return response;
        }
    }
}
