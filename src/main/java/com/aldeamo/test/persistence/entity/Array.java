package com.aldeamo.test.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "arrays")
public class Array {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "input_array")
    private String inputArray;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInputArray() {
        return inputArray;
    }

    public void setInputArray(String inputArray) {
        this.inputArray = inputArray;
    }
}
