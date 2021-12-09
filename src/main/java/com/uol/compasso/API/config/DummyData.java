package com.uol.compasso.API.config;

import com.uol.compasso.API.entity.Product;
import com.uol.compasso.API.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Configuration
public class DummyData {

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    public void createProducts(){

        if (productRepository.count() > 2){
            return;
        }

        Product product = new Product();
        product.setName("Mouse");
        product.setDescription("Produto para controlar o cursor na tela do computador");
        product.setPrice(new BigDecimal("55.00"));

        Product otherProduct = new Product();
        otherProduct.setName("Monitor");
        otherProduct.setDescription("Produto para transmitir imagens");
        otherProduct.setPrice(new BigDecimal("890.00"));

        productRepository.save(product);
        productRepository.save(otherProduct);
    }
}
