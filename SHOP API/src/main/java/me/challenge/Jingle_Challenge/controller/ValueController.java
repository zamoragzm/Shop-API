package me.challenge.Jingle_Challenge.controller;

import me.challenge.Jingle_Challenge.entity.Value;
import me.challenge.Jingle_Challenge.exception.ResourceNotFoundException;
import me.challenge.Jingle_Challenge.repository.AttributeRepository;
import me.challenge.Jingle_Challenge.repository.ProductRepository;
import me.challenge.Jingle_Challenge.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ValueController {

    @Autowired
    private ValueRepository valueRepository;

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/products/{productId}/attributes/{attributeId}/values")
    public Page<Value> getAllValuesByProductAndAttributeId(@PathVariable(value = "productId") Long productId,
                                                @PathVariable(value = "attributeId") Long attributeId,
                                               Pageable pageable) {
        if(!productRepository.existsById(productId)){
            throw new ResourceNotFoundException("ProductId " + productId + " not found");
        }
        return valueRepository.findByAttributeId(attributeId,pageable);
    }

    @PostMapping("/products/{productId}/attributes/{attributeId}/values")
    public Value createValue(@PathVariable (value = "productId") Long productId,
                            @PathVariable (value = "attributeId") Long attributeId,
                             @Valid @RequestBody Value value) {
        return attributeRepository.findByIdAndProductId(attributeId,productId).map(attribute -> {
            value.setAttribute(attribute);
            return valueRepository.save(value);
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId "+productId+" with "+"AttributeId " + attributeId + " not found"));
    }

    @PutMapping("/products/{productId}/attributes/{attributeId}/values/{valueId}")
    public Value updateValue(@PathVariable (value = "productId") Long productId,
                            @PathVariable (value = "attributeId") Long attributeId,
                             @PathVariable (value = "valueId") Long valueId,
                             @Valid @RequestBody Value valueRequest) {
        if(!productRepository.existsById(productId)){
            throw new ResourceNotFoundException("ProductId " + productId + " not found");
        }else if(!attributeRepository.existsById(attributeId)) {
            throw new ResourceNotFoundException("AttributeId " + attributeId + " not found");
        }
        return valueRepository.findById(valueId).map(value -> {
            value.setValue(valueRequest.getValue());
            return valueRepository.save(value);
        }).orElseThrow(() -> new ResourceNotFoundException("ValueId " + valueId + "not found"));
    }

    @DeleteMapping("/products/{productId}/attributes/{attributeId}/values/{valueId}")
    public ResponseEntity<?> deleteValue(@PathVariable (value = "productId") Long productId,
                                       @PathVariable (value = "attributeId") Long attributeId,
                                         @PathVariable (value = "valueId") Long valueId) {
        if(!productRepository.existsById(productId)){
            throw new ResourceNotFoundException("ProductId " + productId + " not found");
        }
        return valueRepository.findByIdAndAttributeId(valueId,attributeId).map(value -> {
            valueRepository.delete(value);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Value not found with id " + valueId + " and attributeId " + attributeId));
    }
}
