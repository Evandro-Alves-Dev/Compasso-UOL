package com.uol.compasso.API.controller;

import com.uol.compasso.API.DTO.productRequest.ProductRequest;
import com.uol.compasso.API.DTO.productResponse.ProductResponse;
import com.uol.compasso.API.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponse> cadastroProduto(@RequestBody @Valid ProductRequest productRequest){
        ProductResponse response = productService.cadastrarProduto(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> alteraProduto(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.alterarProduto(id, productRequest);
        return ResponseEntity.ok(response);
    }
}
