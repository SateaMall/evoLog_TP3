package com.example.evoLog_TP3.service;

import com.example.evoLog_TP3.model.Product;
import com.example.evoLog_TP3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            throw new RuntimeException("Product with this ID already exists.");
        }
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    public Product updateProduct(String id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setExpirationDate(updatedProduct.getExpirationDate());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(String id) {
        Product existingProduct = getProductById(id);
        productRepository.delete(existingProduct);
    }
}