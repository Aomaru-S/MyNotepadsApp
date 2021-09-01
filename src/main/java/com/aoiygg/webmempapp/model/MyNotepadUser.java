package com.aoiygg.webmempapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MyNotepadUser extends User {

    private User user;

    public MyNotepadUser(User user) {
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
        this.user = user;
    }
}
