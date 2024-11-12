package com.example.evoLog_TP3.controller;
import org.springframework.web.bind.annotation.*;
@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/api/products")
public class ProductController {
    @org.springframework.beans.factory.annotation.Autowired
    private com.example.evoLog_TP3.service.ProductService productService;

    // Add a new product
    @org.springframework.web.bind.annotation.PostMapping
    public org.springframework.http.ResponseEntity<?> addProduct(@org.springframework.validation.annotation.Validated
    @org.springframework.web.bind.annotation.RequestBody
    com.example.evoLog_TP3.model.Product product) {
        logger.info("User action logged for method: addProduct");
        try {
            com.example.evoLog_TP3.model.Product createdProduct = productService.addProduct(product);
            return new org.springframework.http.ResponseEntity<>(createdProduct, org.springframework.http.HttpStatus.CREATED);
        } catch (java.lang.RuntimeException ex) {
            return new org.springframework.http.ResponseEntity<>(ex.getMessage(), org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    // Get all products
    @org.springframework.web.bind.annotation.GetMapping
    public org.springframework.http.ResponseEntity<java.util.List<com.example.evoLog_TP3.model.Product>> getAllProducts() {
        logger.info("User action logged for method: getAllProducts");
        return new org.springframework.http.ResponseEntity<>(productService.getAllProducts(), org.springframework.http.HttpStatus.OK);
    }

    // Get product by ID
    @org.springframework.web.bind.annotation.GetMapping("/{id}")
    public org.springframework.http.ResponseEntity<?> getProductById(@org.springframework.web.bind.annotation.PathVariable
    java.lang.String id) {
        logger.info("User action logged for method: getProductById");
        try {
            com.example.evoLog_TP3.model.Product product = productService.getProductById(id);
            return new org.springframework.http.ResponseEntity<>(product, org.springframework.http.HttpStatus.OK);
        } catch (java.lang.RuntimeException ex) {
            return new org.springframework.http.ResponseEntity<>(ex.getMessage(), org.springframework.http.HttpStatus.NOT_FOUND);
        }
    }

    // Update product
    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public org.springframework.http.ResponseEntity<?> updateProduct(@org.springframework.web.bind.annotation.PathVariable
    java.lang.String id, @org.springframework.validation.annotation.Validated
    @org.springframework.web.bind.annotation.RequestBody
    com.example.evoLog_TP3.model.Product product) {
        try {
            com.example.evoLog_TP3.model.Product updatedProduct = productService.updateProduct(id, product);
            return new org.springframework.http.ResponseEntity<>(updatedProduct, org.springframework.http.HttpStatus.OK);
        } catch (java.lang.RuntimeException ex) {
            return new org.springframework.http.ResponseEntity<>(ex.getMessage(), org.springframework.http.HttpStatus.NOT_FOUND);
        }
    }

    // Delete product
    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public org.springframework.http.ResponseEntity<?> deleteProduct(@org.springframework.web.bind.annotation.PathVariable
    java.lang.String id) {
        logger.info("User action logged for method: deleteProduct");
        try {
            productService.deleteProduct(id);
            return new org.springframework.http.ResponseEntity<>("Product deleted successfully.", org.springframework.http.HttpStatus.OK);
        } catch (java.lang.RuntimeException ex) {
            return new org.springframework.http.ResponseEntity<>(ex.getMessage(), org.springframework.http.HttpStatus.NOT_FOUND);
        }
    }
}
