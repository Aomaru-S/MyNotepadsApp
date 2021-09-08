package com.aoiygg.webmempapp;

import com.aoiygg.webmempapp.model.MyNotepadsUser;
import com.aoiygg.webmempapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.aoiygg.webmempapp.model.UserDetailsImpl;

public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyNotepadsUser user = repository.findByMailAddress(username);

        if (user == null) {
            throw new UsernameNotFoundException("Not found username:" + username);
        }

        return new UserDetailsImpl(user);
    }
}
