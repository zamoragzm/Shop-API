package me.challenge.Jingle_Challenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="value")
public class Value {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String value;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="attribute_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Attribute attribute;

    public Value(){
    }

    public Value(String value, Attribute attribute) {
        this.value = value;
        this.attribute = attribute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }


}
