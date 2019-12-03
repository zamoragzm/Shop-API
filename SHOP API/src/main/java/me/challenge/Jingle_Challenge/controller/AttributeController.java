package me.challenge.Jingle_Challenge.controller;

import me.challenge.Jingle_Challenge.entity.Attribute;
import me.challenge.Jingle_Challenge.exception.ResourceNotFoundException;
import me.challenge.Jingle_Challenge.repository.AttributeRepository;
import me.challenge.Jingle_Challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AttributeController {

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private ProductRepository productRepository;



    @GetMapping("/products/{productId}/attributes")
    public Page<Attribute> getAllAttributesByProductId(@PathVariable(value = "productId") Long productId,
                                                   Pageable pageable) {
        return attributeRepository.findByProductId(productId,pageable);
    }

    @PostMapping("/products/{productId}/attributes")
    public Attribute createAttribute(@PathVariable (value = "productId") Long productId,
                             @Valid @RequestBody Attribute attribute) {
        return productRepository.findById(productId).map(product -> {
            attribute.setProduct(product);
            return attributeRepository.save(attribute);
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + productId + " not found"));
    }

    @PutMapping("/products/{productId}/attributes/{attributesId}")
    public Attribute updateAttribute(@PathVariable (value = "productId") Long productId,
                             @PathVariable (value = "attributeId") Long attributeId,
                             @Valid @RequestBody Attribute attributeRequest) {
        if(!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("ProductId " + productId + " not found");
        }

        return attributeRepository.findById(attributeId).map(attribute -> {
            attribute.setAttributeName(attributeRequest.getAttributeName());
            return attributeRepository.save(attribute);
        }).orElseThrow(() -> new ResourceNotFoundException("AttributeId " + attributeId + "not found"));
    }

    @DeleteMapping("/products/{productId}/attributes/{attributeId}")
    public ResponseEntity<?> deleteAttribute(@PathVariable (value = "productId") Long productId,
                                         @PathVariable (value = "attributeId") Long attributeId) {
        return attributeRepository.findByIdAndProductId(attributeId, productId).map(attribute -> {
            attributeRepository.delete(attribute);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Attribute not found with id " + attributeId + " and productId " + productId));
    }
}
