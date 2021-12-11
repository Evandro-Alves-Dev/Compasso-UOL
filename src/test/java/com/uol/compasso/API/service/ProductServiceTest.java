package com.uol.compasso.API.service;

import com.uol.compasso.API.DTO.productRequest.ProductRequest;
import com.uol.compasso.API.DTO.productResponse.ProductResponse;
import com.uol.compasso.API.entity.Product;
import com.uol.compasso.API.mapper.MapperProductRequestToProduct;
import com.uol.compasso.API.mapper.MapperProductToProductResponse;
import com.uol.compasso.API.repository.ProductRepository;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private MapperProductRequestToProduct mapperProductRequestToProduct;

    @Mock
    private MapperProductToProductResponse mapperProductToProductResponse;

    @Autowired
    private ProductRequest productRequest;

    @Before
    public void setUp(){
        productRequest = new ProductRequest("Teclado",
                "Produto para inderir informações ao computador", new BigDecimal("149.00"));
    }

    @Test
    public void cadastrarProdutoTest(){
        when(mapperProductRequestToProduct.toDto(any())).thenReturn(new Product());
        when(productRepository.save(any())).thenReturn(new Product());
        when(mapperProductToProductResponse.toDto(any())).thenReturn(new ProductResponse());

        this.productService.cadastrarProduto(productRequest);
    }
}