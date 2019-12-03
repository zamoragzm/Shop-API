package me.challenge.Jingle_Challenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="attribute")
public class Attribute {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String attributeName;

    @OneToMany(mappedBy="attribute", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Value> values;


    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    public Attribute(){
        this.values = new ArrayList<>();
    }

    public Attribute(String attributeName, Product product) {
        this.attributeName = attributeName;
        this.product = product;
        this.values = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
