package com.aoiygg.webmempapp.model;

import com.aoiygg.webmempapp.model.key.NotepadCategoryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notepad_category")
@IdClass(value = NotepadCategoryKey.class)
public class NotepadCategory {
    @Id
    private long notepadId;
    @Id
    private String categoryName;
}
