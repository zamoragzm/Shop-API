package me.challenge.Jingle_Challenge.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productNumber;
    private Double price;

    @OneToMany(mappedBy="product", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Category> categories;
    @OneToMany(mappedBy="product", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Store> stores;
    @OneToMany(mappedBy="product", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Attribute> attributes;

    public Product(){
        this.categories = new ArrayList<>();
        this.stores = new ArrayList<>();
        this.attributes = new ArrayList<>();

    }

    public Product(String productName, String productNumber, Double price) {
        this.productName = productName;
        this.productNumber = productNumber;
        this.price = price;
        this.categories = new ArrayList<>();
        this.stores = new ArrayList<>();
        this.attributes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
