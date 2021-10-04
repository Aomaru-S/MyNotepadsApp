package com.aoiygg.webmempapp.model.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotepadCategoryKey implements Serializable {
    private long notepadId;
    private String categoryName;
}
