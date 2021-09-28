package com.aoiygg.webmempapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "notepad")
public class Notepad {

    @Id
    @Column(name = "notepad_id")
    @GeneratedValue
    private long notepadId;

    private String title;

    private String body;

    @Column(name = "mail_address")
    private String mailAddress;

    @ManyToMany
    @JoinTable(name = "notepad_category",
            joinColumns = @JoinColumn(name = "notepad_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categoryList = new ArrayList<>();

    public void setCategoryList(List<String> catList) {

        List<Category> categoryList = new ArrayList<>();
        catList.forEach(s -> {
            Category category = new Category(mailAddress, s);
            categoryList.add(category);
        });
        this.categoryList = categoryList;
    }
}
