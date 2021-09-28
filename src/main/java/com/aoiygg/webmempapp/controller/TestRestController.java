package com.aoiygg.webmempapp.controller;

import com.aoiygg.webmempapp.repository.AuthMailAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestRestController {

    AuthMailAddressRepository repository;

    @Autowired
    public TestRestController(AuthMailAddressRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/test")
    public boolean test(JpaTransactionManager jtm) {
        System.out.println(jtm == null);
        return jtm == null;
    }
}
