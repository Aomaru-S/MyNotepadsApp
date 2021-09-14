package com.aoiygg.webmempapp.repository;

import com.aoiygg.webmempapp.model.AuthMailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthMailAddressRepository extends JpaRepository<AuthMailAddress, String> {
    AuthMailAddress findAuthMailAddressByUuid(String uuid);
}
