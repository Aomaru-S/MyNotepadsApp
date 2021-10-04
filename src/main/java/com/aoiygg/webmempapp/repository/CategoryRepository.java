package com.aoiygg.webmempapp.repository;

import com.aoiygg.webmempapp.model.Category;
import com.aoiygg.webmempapp.model.key.CategoryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, CategoryKey> {
    List<Category> findCategoryByMailAddress(String mailAddress);
}
