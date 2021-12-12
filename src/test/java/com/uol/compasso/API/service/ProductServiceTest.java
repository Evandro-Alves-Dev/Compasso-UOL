package com.uol.compasso.API.service;

import com.uol.compasso.API.DTO.productRequest.ProductRequest;
import com.uol.compasso.API.DTO.productResponse.ProductResponse;
import com.uol.compasso.API.entity.Product;
import com.uol.compasso.API.mapper.MapperProductRequestToProduct;
import com.uol.compasso.API.mapper.MapperProductToProductResponse;
import com.uol.compasso.API.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

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

    Long id;
    Optional<Product> product;
    Product product2;
    List<Product> productList = new ArrayList<>();
    BigDecimal max_price;
    BigDecimal min_price;
    String q;

    @Before
    public void setUp(){
        productRequest = new ProductRequest("Teclado",
                "Produto para inserir informações ao computador", new BigDecimal("149.00"));
        id = 1L;
        product = Optional.of(new Product(1L, "Tecaldo novo",
                "Produto para inserir informações ao computador", new BigDecimal("299.00")));
        product2 = new Product(1L, "Cabo HDMI",
                "Cabo para transferir audio e video da alta qualidade para um monitor", new BigDecimal("45.00"));
        productList.add(product2);
        max_price = new BigDecimal("900.00");
        min_price = new BigDecimal("50.00");
        q = "Ca";
    }

    @Test
    public void cadastrarProdutoTest(){
        when(mapperProductRequestToProduct.toDto(any())).thenReturn(new Product());
        when(productRepository.save(any())).thenReturn(new Product());
        when(mapperProductToProductResponse.toDto(any())).thenReturn(new ProductResponse());

        this.productService.cadastrarProduto(productRequest);
    }

    @Test
    public void alterarProdutoTest(){
        when(productRepository.findById(id)).thenReturn(product);
        when(productRepository.save(any())).thenReturn(new Product());
        when(mapperProductToProductResponse.toDto(any())).thenReturn(new ProductResponse());

        this.productService.alterarProduto(id, productRequest);
    }

    @Test
    public void retornarProdutoTest(){
        when(productRepository.findById(id)).thenReturn(product);
        when(mapperProductToProductResponse.toDto(any())).thenReturn(new ProductResponse());

        this.productService.retornarProduto(id);
    }

    @Test
    public void retornarListaProdutosTest(){
        when(productRepository.findAll()).thenReturn(productList);

        this.productService.retornarListaProdutos();
    }

    @Test
    public void procurarProdutosTest(){
        when(productRepository.findAll()).thenReturn(productList);

        this.productService.procurarProdutos(max_price, min_price, q);
    }

    @Test
    public void deletarProdutoTest(){
        when(productRepository.findById(id)).thenReturn(product);

        this.productService.deletarProduto(id);
    }
}