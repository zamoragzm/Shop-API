package me.challenge.Jingle_Challenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Category {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String categoryName;


    @ManyToOne(fetch= FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    public Category(){

    }

    public Category(String categoryName, Product product) {
        this.categoryName = categoryName;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }




}
