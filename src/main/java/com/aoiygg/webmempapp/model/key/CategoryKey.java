package com.aoiygg.webmempapp.model.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryKey implements Serializable {
    private String categoryName;
    private String mailAddress;
}
