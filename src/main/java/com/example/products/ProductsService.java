package com.example.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getProductById(Long productId) {
        Optional<Product> product =  productRepository.findById(productId);

        if(productRepository.findById(productId).isPresent()){
            return product;
        }
        else{
            return Optional.empty();
        }
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Optional<Product> createProduct(Product product) {

        Example<Product> example = Example.of(product);

        Optional<Product> optionalProduct = productRepository.findOne(example);

        if(!optionalProduct.isPresent()){

            return Optional.of(productRepository.save(product));

        }
        else {
            return Optional.empty();
        }

    }

    public ResponseEntity<Product> updateProduct(Long productId, Product product) {

        Optional<Product> existingProductOptional = productRepository.findById(productId);
        if (existingProductOptional.isPresent()) {
            Product updatedProduct = productRepository.save(product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

        }
    else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
