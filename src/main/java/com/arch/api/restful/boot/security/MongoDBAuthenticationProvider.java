package com.arch.api.restful.boot.security;

import com.arch.api.restful.boot.beans.Customer;
import com.arch.api.restful.boot.db.repositories.CustomerMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SRINIVASULU on 07/11/16.
 */
@Component
public class MongoDBAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomerMongoRepository customerMongoRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        List<Customer> customers = customerMongoRepository.findByEmailAndPasswd(authentication.getPrincipal().toString(),
                authentication.getCredentials().toString());

        if(customers.size() == 1){

            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority(customers.get(0).getType()));
            return new UsernamePasswordAuthenticationToken(authentication.getName(),
                    authentication.getCredentials(), grantedAuths);

        }

        throw new BadCredentialsException("Username/Password does not match for " + authentication.getPrincipal());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
