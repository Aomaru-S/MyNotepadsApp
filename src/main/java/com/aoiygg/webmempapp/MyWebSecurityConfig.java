package com.aoiygg.webmempapp;

import com.aoiygg.webmempapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    UserRepository repository;

    @Autowired
    public MyWebSecurityConfig(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void configure(WebSecurity web) /*throws Exception*/ {
        web.ignoring().antMatchers("/css/**", "/image/**", "/javascript/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/myNotepads").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        UserDetailsServiceImpl service = new UserDetailsServiceImpl(repository);
        auth.userDetailsService(service);
    }
}