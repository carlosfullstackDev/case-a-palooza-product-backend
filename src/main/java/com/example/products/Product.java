package com.example.products;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price")
    private double price;

    private Product() {}

    public static class Builder {
        private final Long id;
        private final String name;
        private final double price;
        private String description = "";
        private String imageUrl = "";

        public Builder(Long id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.id = this.id;
            product.name = this.name;
            product.price = this.price;
            product.description = this.description;
            product.imageUrl = this.imageUrl;
            return product;
        }
    }

    // getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }
}