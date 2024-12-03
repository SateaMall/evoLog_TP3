
package com.example.evoLog_TP3.controller;

import com.example.evoLog_TP3.model.Product;
import com.example.evoLog_TP3.service.ProductService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping({ "/api/products" })
public class ProductController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(com.example.evoLog_TP3.controller.ProductController.class);

    @org.springframework.beans.factory.annotation.Autowired
    private com.example.evoLog_TP3.service.ProductService productService;

    public ProductController() {
    }

    @org.springframework.web.bind.annotation.PostMapping
    public org.springframework.http.ResponseEntity<?> addProduct(@org.springframework.web.bind.annotation.RequestHeader("userId")
                                                                 java.lang.String userId, @org.springframework.validation.annotation.Validated
                                                                 @org.springframework.web.bind.annotation.RequestBody
                                                                 com.example.evoLog_TP3.model.Product product) {
        logger.info("User action logged for user: " + userId + " in method: addProduct");;
        try {
            com.example.evoLog_TP3.model.Product createdProduct = this.productService.addProduct(product);
            return new org.springframework.http.ResponseEntity(createdProduct, org.springframework.http.HttpStatus.CREATED);
        } catch (java.lang.RuntimeException var4) {
            java.lang.RuntimeException ex = var4;
            return new org.springframework.http.ResponseEntity(ex.getMessage(), org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    @org.springframework.web.bind.annotation.GetMapping
    public org.springframework.http.ResponseEntity<java.util.List<com.example.evoLog_TP3.model.Product>> getAllProducts(@org.springframework.web.bind.annotation.RequestHeader("userId")
                                                                                                                        java.lang.String userId) {
        logger.info("User action logged for user: " + userId + " in method: getAllProducts");;
        java.util.List<com.example.evoLog_TP3.model.Product> products = this.productService.getAllProducts();
        return new org.springframework.http.ResponseEntity(products, org.springframework.http.HttpStatus.OK);
    }

    @org.springframework.web.bind.annotation.GetMapping({ "/{id}" })
    public org.springframework.http.ResponseEntity<?> getProductById(@org.springframework.web.bind.annotation.RequestHeader("userId")
                                                                     java.lang.String userId, @org.springframework.web.bind.annotation.PathVariable
                                                                     java.lang.String id) {
        try {    Product product = productService.getProductById(id);    logger.info("User action logged for user: " + userId + " in method: getProductById with product price: " + product.getPrice());} catch (RuntimeException e) {    logger.info("User action logged for user: " + userId + " in method: getProductById but no product price could be retrieved.");};
        try {
            com.example.evoLog_TP3.model.Product product = this.productService.getProductById(id);
            return new org.springframework.http.ResponseEntity(product, org.springframework.http.HttpStatus.OK);
        } catch (java.lang.RuntimeException var4) {
            java.lang.RuntimeException ex = var4;
            return new org.springframework.http.ResponseEntity(ex.getMessage(), org.springframework.http.HttpStatus.NOT_FOUND);
        }
    }

    @org.springframework.web.bind.annotation.PutMapping({ "/{id}" })
    public org.springframework.http.ResponseEntity<?> updateProduct(@org.springframework.web.bind.annotation.RequestHeader("userId")
                                                                    java.lang.String userId, @org.springframework.web.bind.annotation.PathVariable
                                                                    java.lang.String id, @org.springframework.validation.annotation.Validated
                                                                    @org.springframework.web.bind.annotation.RequestBody
                                                                    com.example.evoLog_TP3.model.Product product) {
        logger.info("User action logged for user: " + userId + " in method: updateProduct");;
        try {
            com.example.evoLog_TP3.model.Product updatedProduct = this.productService.updateProduct(id, product);
            return new org.springframework.http.ResponseEntity(updatedProduct, org.springframework.http.HttpStatus.OK);
        } catch (java.lang.RuntimeException var5) {
            java.lang.RuntimeException ex = var5;
            return new org.springframework.http.ResponseEntity(ex.getMessage(), org.springframework.http.HttpStatus.NOT_FOUND);
        }
    }

    @org.springframework.web.bind.annotation.DeleteMapping({ "/{id}" })
    public org.springframework.http.ResponseEntity<?> deleteProduct(@org.springframework.web.bind.annotation.RequestHeader("userId")
                                                                    java.lang.String userId, @org.springframework.web.bind.annotation.PathVariable
                                                                    java.lang.String id) {
        logger.info("User action logged for user: " + userId + " in method: deleteProduct");;
        try {
            this.productService.deleteProduct(id);
            return new org.springframework.http.ResponseEntity("Product deleted successfully.", org.springframework.http.HttpStatus.OK);
        } catch (java.lang.RuntimeException var4) {
            java.lang.RuntimeException ex = var4;
            return new org.springframework.http.ResponseEntity(ex.getMessage(), org.springframework.http.HttpStatus.NOT_FOUND);
        }
    }
}
