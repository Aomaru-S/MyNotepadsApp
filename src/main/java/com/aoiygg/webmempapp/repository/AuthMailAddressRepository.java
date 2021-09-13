package com.aoiygg.webmempapp.repository;

import com.aoiygg.webmempapp.model.AuthMailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMailAddressRepository extends JpaRepository<AuthMailAddress, String> {
    int countByMailAddress(String mailAddress);
    void deleteByMailAddress(String mailAddress);
}
