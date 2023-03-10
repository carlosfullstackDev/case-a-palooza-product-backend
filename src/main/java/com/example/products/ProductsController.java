package com.example.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/{productId}") public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Optional<Product> products = productsService.getProductById(productId);
        if (!products.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        return new ResponseEntity<>(products.get(), HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllproducts() {
        try {
            List<Product> products = productsService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> createproduct(@RequestBody Product products) {
        Optional<Product> createdproducts = productsService.createProduct(products);

        return createdproducts.map(product -> new ResponseEntity<>(product, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateproduct(@PathVariable Long productId, @RequestBody Product products) {
        ResponseEntity<Product>  response = productsService.updateProduct(productId,products);

        return response;
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteproduct(@PathVariable Long productId) {
        productsService.deleteProduct(productId);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
