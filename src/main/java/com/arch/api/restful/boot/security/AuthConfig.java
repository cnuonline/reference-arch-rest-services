package com.arch.api.restful.boot.security;

import org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by SRINIVASULU on 31/10/16.
 */
@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //TODO: change the Memory Authentication to JDBC authentication /LDAP authentication
        auth.inMemoryAuthentication()
                .withUser("srinik").password("secret1").roles("USER")
                .and()
                .withUser("user2").password("secret2").roles("USER");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.httpBasic();
        http.csrf().disable();
    }
}