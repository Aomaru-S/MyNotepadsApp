package com.aoiygg.webmempapp.repository;

import com.aoiygg.webmempapp.model.NotepadCategory;
import com.aoiygg.webmempapp.model.key.NotepadCategoryKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotepadCategoryRepository extends JpaRepository<NotepadCategory, NotepadCategoryKey> {
    List<NotepadCategory> findNotepadCategoryByCategoryName(String categoryName);

    List<NotepadCategory> findNotepadCategoryByNotepadId(Long id);

    long countAllByCategoryNameAndNotepadId(String categoryName, long notepadId);
}
