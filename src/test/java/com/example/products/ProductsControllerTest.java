package com.example.products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class ProductsControllerTest {

    @Mock
    private ProductsService productsService;

    @InjectMocks
    private ProductsController productsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductReturnsProduct() {
        Long productId = 1L;
        Product product =  Product.builder().description("Test product 1").build();
        when(productsService.getProductById(productId)).thenReturn(Optional.of(product));

        ResponseEntity<Product> responseEntity = productsController.getProduct(productId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }

    @Test
    void testGetProductReturnsNotFound() {
        Long productId = 1L;
        when(productsService.getProductById(productId)).thenReturn(Optional.empty());

        ResponseEntity<Product> responseEntity = productsController.getProduct(productId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testGetAllProductsReturnsProducts() {
        Product product1 =  Product.builder().description("Test product 1").build();
        Product product2 =  Product.builder().description("Test product 2").build();
        List<Product> products = Arrays.asList(product1, product2);
        when(productsService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<Product>> responseEntity = productsController.getAllproducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(products, responseEntity.getBody());
    }

    @Test
    void testGetAllProductsReturnsInternalServerError() {
        when(productsService.getAllProducts()).thenThrow(new RuntimeException());

        ResponseEntity<List<Product>> responseEntity = productsController.getAllproducts();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testCreateProductReturnsCreatedProduct() {
        Product product =  Product.builder().description("Test product 1").build();
        when(productsService.createProduct(any(Product.class))).thenReturn(Optional.of(product));

        ResponseEntity<Product> responseEntity = productsController.createproduct(product);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }

    @Test
    void testCreateProductReturnsBadRequest() {
        when(productsService.createProduct(any(Product.class))).thenReturn(Optional.empty());

        ResponseEntity<Product> responseEntity = productsController.createproduct( Product.builder().build());

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testUpdateProductReturnsUpdatedProduct() {
        Long productId = 1L;
        Product product = Product.builder().description("Test product 1").build();
        when(productsService.updateProduct(eq(productId), any(Product.class))).thenReturn(new ResponseEntity<>(product, HttpStatus.OK));

        ResponseEntity<Product> responseEntity = productsController.updateproduct(productId, product);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }

    @Test
    void testDeleteProductReturnsOK() {
        Long productId = 1L;

        ResponseEntity<Product> responseEntity = productsController.deleteproduct(productId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());

        verify(productsService, times(1)).deleteProduct(productId);
    }
}