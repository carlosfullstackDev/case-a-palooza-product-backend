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

    private Product() {
        // private constructor to enforce the use of the builder pattern
    }

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private String imageUrl;
        private double price;

        private Builder() {
            // private constructor to enforce the use of the static builder method
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.id = this.id;
            product.name = this.name;
            product.description = this.description;
            product.imageUrl = this.imageUrl;
            product.price = this.price;
            return product;
        }
    }
}
