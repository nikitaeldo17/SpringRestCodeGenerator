package com.example.SpringRestCodeGenerator.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "stock")
    private int stock;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "category")
    private String category;
    @Column(name = "brand")
    private String brand;
    @Column(name = "color")
    private String color;
}
