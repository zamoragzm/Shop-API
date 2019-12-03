package me.challenge.Jingle_Challenge.controller;

import me.challenge.Jingle_Challenge.entity.Store;
import me.challenge.Jingle_Challenge.exception.ResourceNotFoundException;
import me.challenge.Jingle_Challenge.repository.ProductRepository;
import me.challenge.Jingle_Challenge.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/products/{productId}/stores")
    public Page<Store> getAllStoresByProductId(@PathVariable(value = "productId") Long productId,
                                               Pageable pageable) {
        return storeRepository.findByProductId(productId,pageable);
    }

    @PostMapping("/products/{productId}/stores")
    public Store createStore(@PathVariable (value = "productId") Long productId,
                                   @Valid @RequestBody Store store) {
        return productRepository.findById(productId).map(product -> {
            store.setProduct(product);
            return storeRepository.save(store);
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + productId + " not found"));
    }

    @PutMapping("/products/{productId}/stores/{storeId}")
    public Store updateStore(@PathVariable (value = "productId") Long productId,
                                   @PathVariable (value = "storeId") Long storeId,
                                   @Valid @RequestBody Store storeRequest) {
        if(!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("ProductId " + productId + " not found");
        }

        return storeRepository.findById(storeId).map(store -> {
            store.setStoreName(storeRequest.getStoreName());
            store.setAddress(storeRequest.getAddress());
            store.setAvailability(storeRequest.getAvailability());
            return storeRepository.save(store);
        }).orElseThrow(() -> new ResourceNotFoundException("StoreId " + storeId + "not found"));
    }

    @DeleteMapping("/products/{productId}/stores/{storeId}")
    public ResponseEntity<?> deleteStore(@PathVariable (value = "productId") Long productId,
                                            @PathVariable (value = "storeId") Long storeId) {
        return storeRepository.findByIdAndProductId(storeId, productId).map(store -> {
            storeRepository.delete(store);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Store not found with id " + storeId + " and productId " + productId));
    }
}
