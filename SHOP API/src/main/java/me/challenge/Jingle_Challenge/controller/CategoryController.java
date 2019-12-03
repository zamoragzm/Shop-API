package me.challenge.Jingle_Challenge.controller;


import me.challenge.Jingle_Challenge.entity.Category;
import me.challenge.Jingle_Challenge.exception.ResourceNotFoundException;
import me.challenge.Jingle_Challenge.repository.CategoryRepository;
import me.challenge.Jingle_Challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/products/{productId}/categories")
    public Page<Category> getAllCategoriesByProductId(@PathVariable (value = "productId") Long productId,
                                                      Pageable pageable) {
        return categoryRepository.findByProductId(productId, pageable);
    }

    @PostMapping("/products/{productId}/categories")
    public Category createCategory(@PathVariable (value = "productId") Long productId,
                                 @Valid @RequestBody Category category) {
        return productRepository.findById(productId).map(product -> {
            category.setProduct(product);
            return categoryRepository.save(category);
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + productId + " not found"));
    }

    @PutMapping("/products/{productId}/categories/{categoryId}")
    public Category updateCategory(@PathVariable (value = "productId") Long productId,
                                 @PathVariable (value = "categoryId") Long categoryId,
                                 @Valid @RequestBody Category categoryRequest) {
        if(!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("ProductId " + productId + " not found");
        }

        return categoryRepository.findById(categoryId).map(category -> {
            category.setCategoryName(categoryRequest.getCategoryName());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + categoryId + "not found"));
    }

    @DeleteMapping("/products/{productId}/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable (value = "productId") Long productId,
                                           @PathVariable (value = "categoryId") Long categoryId) {
        return categoryRepository.findByIdAndProductId(categoryId, productId).map(category -> {
            categoryRepository.delete(category);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId + " and productId " + productId));
    }

}
