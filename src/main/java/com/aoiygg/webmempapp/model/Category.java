package com.aoiygg.webmempapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Getter @Setter
    private Long categoryId;

    @Getter @Setter
    @Column(name = "mail_address")
    private String mailAddress;

    @Getter @Setter
    @Column(name = "category_name")
    private String categoryName;

    @Setter
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "categoryList")
    private List<Notepad> notepadList = new ArrayList<>();
}
