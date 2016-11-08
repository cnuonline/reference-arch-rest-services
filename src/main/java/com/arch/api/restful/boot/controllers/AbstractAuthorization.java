package com.arch.api.restful.boot.controllers;

import com.arch.api.restful.boot.beans.Customer;
import com.arch.api.restful.boot.db.repositories.CustomerMongoRepository;
import com.jcabi.aspects.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientResponseException;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;

/**
 * Created by SRINIVASULU on 07/11/16.
 */
public class AbstractAuthorization {

    @Autowired
    CustomerMongoRepository customerMongoRepository;

    @Loggable(value = Loggable.WARN, name = "validateAuthorization method")
    protected Customer validateAuthorization(String authorization ) {

        if(StringUtils.isEmpty(authorization)) {
            throw new RestClientResponseException("Basic Authorization header is missing",
                    HttpStatus.FORBIDDEN.value(), "Access Denied",
                    null, null, null);
        }

        String base64Credentials = authorization.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                Charset.forName("UTF-8"));
        // credentials = username:password
        final String[] values = credentials.split(":",2);

        if(values.length != 2) {
            throw new RestClientResponseException("Credentials are missing . user , password .",
                    HttpStatus.FORBIDDEN.value(), "Access Denied",
                    null, null, null);
        }

        String email = values[0];
        String password = values[1];

       List<Customer> customers = customerMongoRepository.findByEmailAndPasswd(email,password);

        if(customers.size() > 1){
            throw new RestClientResponseException("Credentials are invalid", HttpStatus.FORBIDDEN.value(), "Access Denied",
                    null, null, null);
        }

        return customers.get(0);
    }


    @Loggable(value = Loggable.WARN, name = "validateRegistrationData method")
    protected void validateRegistrationData(Customer requestCustomer) {

        if(StringUtils.isEmpty(requestCustomer.getEmail()) || StringUtils.isEmpty(requestCustomer.getPasswd())
                || StringUtils.isEmpty(requestCustomer.getType()) || StringUtils.isEmpty(requestCustomer.getCompany())){

            throw new RestClientResponseException("Invalid Data and fields are missing", HttpStatus.BAD_REQUEST.value(), "Access Denied",
                    null, null, null);
        }

        //TODO: Add mandatory fields for PAN with Tax Payer

        //TODO: Add mandatory fields for TaxConsultant with Company

        //TODO: Add mandatory fields for Institutional Manager, Institutional Delegate with Company, phone
    }
}
