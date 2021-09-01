package com.aoiygg.webmempapp.repository;

import com.aoiygg.webmempapp.model.Notepad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotepadRepository extends JpaRepository<Notepad, Long> {
}
