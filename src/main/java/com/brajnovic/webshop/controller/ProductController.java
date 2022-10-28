package com.brajnovic.webshop.controller;

import com.brajnovic.webshop.model.Product;
import com.brajnovic.webshop.model.User;
import com.brajnovic.webshop.service.ProductsService;
import com.brajnovic.webshop.util.UrlConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(UrlConstants.APP_CONTEXT_ROOT)
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping(UrlConstants.GET_PRODUCTS)
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> productList;

        try {
            productList = productsService.getProducts();
            log.info("Products found: " + productList.size() + ".");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.noContent().header("errorMessage", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("errorMessage", e.getMessage()).build();
        }

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
