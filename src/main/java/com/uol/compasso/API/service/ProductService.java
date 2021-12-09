package com.uol.compasso.API.service;

import com.uol.compasso.API.DTO.productRequest.ProductRequest;
import com.uol.compasso.API.DTO.productResponse.ProductResponse;
import com.uol.compasso.API.entity.Product;
import com.uol.compasso.API.exceptions.DescriptionException;
import com.uol.compasso.API.exceptions.NameException;
import com.uol.compasso.API.exceptions.PriceException;
import com.uol.compasso.API.exceptions.ProductException;
import com.uol.compasso.API.mapper.MapperProductRequestToProduct;
import com.uol.compasso.API.mapper.MapperProductToProductResponse;
import com.uol.compasso.API.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MapperProductToProductResponse mapperProductToProductResponse;

    @Autowired
    private MapperProductRequestToProduct mapperProductRequestToProduct;

    public ProductResponse cadastrarProduto(ProductRequest productRequest){
        validaProduto(productRequest);

        Product product = mapperProductRequestToProduct.toDto(productRequest);

        Product saved = productRepository.save(product);

        ProductResponse response = mapperProductToProductResponse.toDto(saved);

        return response;
    }

    public ProductResponse alterarProduto(Long id, ProductRequest productRequest) {
        validaProduto(productRequest);

        Optional<Product> existsProduct = productRepository.findById(id);

        if (Objects.isNull(existsProduct) || existsProduct.isEmpty() ){
            throw new ProductException("Id informado não existe");
        }

        existsProduct.map( n ->{
            n.setName(productRequest.getName());
            n.setDescription(productRequest.getDescription());
            n.setPrice(productRequest.getPrice());
            return mapperProductRequestToProduct;
        });

        Product saved = productRepository.save(existsProduct.get());

        ProductResponse response = mapperProductToProductResponse.toDto(saved);

        return response;
    }

    public ProductResponse retornarProduto(Long id) {
        Optional<Product> existsProduct = productRepository.findById(id);

        if (Objects.isNull(existsProduct) || existsProduct.isEmpty() ){
            throw new ProductException("Id informado não existe");
        }

        ProductResponse response = mapperProductToProductResponse.toDto(existsProduct.get());

        return response;

    }

    public List<ProductResponse> retornarListaProdutos() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponse> productResponseList = productList.stream().map(product -> {
            ProductResponse productResponse = mapperProductToProductResponse.toDto(product);
            return productResponse;
        }).collect(Collectors.toList());

        return productResponseList;
    }

    public void deletarProduto(Long id) {
        Optional<Product> existsProduct = productRepository.findById(id);

        if (Objects.isNull(existsProduct) || existsProduct.isEmpty() ) {
            throw new ProductException("Id informado não existe");
        }

        productRepository.deleteById(id);
    }

    public void validaProduto(ProductRequest productRequest){
        int priceChecked = productRequest.getPrice().compareTo(new BigDecimal("0.1"));

        if (Objects.isNull(productRequest.getName()) || productRequest.getName().isEmpty()) {
            throw new NameException("Nome não pode ser nulo");
        }else if (Objects.isNull(productRequest.getDescription()) || productRequest.getDescription().isEmpty()){
            throw new DescriptionException("Descrição não pode estar nula");
        }else if (Objects.isNull(productRequest.getPrice())){
            throw new PriceException("Preço não pode ser nulo");
        }else if (priceChecked < 0){
            throw new PriceException("Preço deve conter valor positivo");
        }
    }
}
