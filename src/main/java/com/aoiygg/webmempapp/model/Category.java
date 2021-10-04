package com.aoiygg.webmempapp.model;

import com.aoiygg.webmempapp.model.key.CategoryKey;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@IdClass(value = CategoryKey.class)
public class Category implements Serializable {

    @Id
    @Column(name = "category_name")
    private String categoryName;

    @Id
    @Column(name = "mail_address")
    private String mailAddress;
}
