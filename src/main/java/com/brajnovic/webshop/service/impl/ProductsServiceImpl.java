package com.brajnovic.webshop.service.impl;

import com.brajnovic.webshop.entity.ProductEntity;
import com.brajnovic.webshop.entity.UserEntity;
import com.brajnovic.webshop.model.Product;
import com.brajnovic.webshop.model.User;
import com.brajnovic.webshop.repository.ProductsRepository;
import com.brajnovic.webshop.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsServiceImpl(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Product> getProducts() throws EntityNotFoundException {
        List<Product> productList = new ArrayList<>();

        List<ProductEntity> productEntityArrayList = new ArrayList<>(productsRepository.findAll());

        productEntityArrayList.forEach(productEntity -> productList.add(ProductEntity.convertToProduct(productEntity)));

        return productList;
    }

}
