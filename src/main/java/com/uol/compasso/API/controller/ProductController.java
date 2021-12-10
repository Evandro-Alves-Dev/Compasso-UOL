package com.uol.compasso.API.controller;

import com.uol.compasso.API.DTO.productRequest.ProductRequest;
import com.uol.compasso.API.DTO.productResponse.ProductResponse;
import com.uol.compasso.API.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

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

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductResponse> retornaProduto(@PathVariable Long id){
        ProductResponse response = productService.retornarProduto(id);
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<ProductResponse>> procuraProdutos(BigDecimal max_price, BigDecimal min_price, String q){
        List<ProductResponse> responseList = productService.procurarProdutos(max_price, min_price, q);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> retornaListaProdutos(){
        List<ProductResponse> responseList = productService.retornarListaProdutos();
        return ResponseEntity.ok(responseList);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deletaProduto(@PathVariable Long id){
        productService.deletarProduto(id);
    }
}
