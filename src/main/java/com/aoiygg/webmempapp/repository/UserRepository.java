package com.aoiygg.webmempapp.repository;

import com.aoiygg.webmempapp.model.MyNotepadsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyNotepadsUser, String> {
    MyNotepadsUser findByName(String username);
}
