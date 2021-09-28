package com.aoiygg.webmempapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "category")
public class Category {

    public Category(String mailAddress, String categoryName) {
        this.mailAddress = mailAddress;
        this.categoryName = categoryName;
    }

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "mail_address")
    private String mailAddress;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "categoryList")
    private List<Notepad> notepadList = new ArrayList<>();
}
