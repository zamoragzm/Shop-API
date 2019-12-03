package me.challenge.Jingle_Challenge.controller;


import me.challenge.Jingle_Challenge.entity.Product;
import me.challenge.Jingle_Challenge.exception.ResourceNotFoundException;
import me.challenge.Jingle_Challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @PostMapping("/products")
    public Product createPost(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{productId}")
    public Product updatePost(@PathVariable Long productId, @Valid @RequestBody Product productRequest) {
        return productRepository.findById(productId).map(product -> {
            product.setProductName(productRequest.getProductName());
            product.setProductNumber(productRequest.getProductNumber());
            product.setPrice(productRequest.getPrice());
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + productId + " not found"));
    }


    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "productId") Long productId) {
        return productRepository.findById(productId).map(post -> {
            productRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + productId + " not found"));
    }


}
