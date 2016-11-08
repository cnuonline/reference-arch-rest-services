package com.arch.api.restful.boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by SRINIVASULU on 31/10/16.
 */
//Enable web security when it is configured.
@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MongoDBAuthenticationProvider mongoDBAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //TODO: change the Memory Authentication to JDBC authentication /LDAP authentication


       auth.authenticationProvider(mongoDBAuthenticationProvider);


    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/customer/register").permitAll()
                .anyRequest().fullyAuthenticated();
        http.httpBasic();
        http.csrf().disable();
    }
}