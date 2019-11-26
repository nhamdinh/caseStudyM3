package com.codegym.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class LoaiHang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    public List<Hang> getCustomers() {
        return books;
    }

    public void setCustomers(List<Hang> books) {
        this.books = books;
    }

    @OneToMany(targetEntity = Hang.class)
    private List<Hang> books;

    public LoaiHang() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
