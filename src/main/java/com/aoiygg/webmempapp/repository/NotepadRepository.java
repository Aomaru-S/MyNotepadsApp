package com.aoiygg.webmempapp.repository;

import com.aoiygg.webmempapp.model.Notepad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotepadRepository extends JpaRepository<Notepad, Long> {
}
