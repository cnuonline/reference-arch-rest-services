package com.arch.api.restful.boot.controllers;

import com.arch.api.restful.boot.beans.Customer;
import com.jcabi.aspects.Loggable;
import com.jcabi.aspects.RetryOnFailure;
import io.swagger.annotations.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;

import java.util.Date;


@RestController
@RequestMapping("/customer")
public class CustomerController extends  AbstractAuthorization{

  //  private static final String template = "Hello, %s!";
  //  private final AtomicLong counter = new AtomicLong();


    @Loggable(value=Loggable.WARN)
    @RequestMapping(path = "/getCustomer" , method = RequestMethod.GET,consumes = MediaType.ALL_VALUE)
    @ApiOperation(value = "getCustomer", nickname = "getCustomer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Customer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public Customer getCustomer(@RequestHeader(value = "Authorization") String authorization) {

       Customer customer = validateAuthorization(authorization);
      //  process(name);
        return customer;
    }





    @Loggable(value=Loggable.WARN)
    @RequestMapping(path = "/register" , method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "register", nickname = "registerCustomer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Customer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public Customer register( @RequestBody Customer requestCustomer) {
         //validate Mandatory Registration
            validateRegistrationData(requestCustomer);

            requestCustomer.setLastUpdated(new Date());
            requestCustomer.setCreatedAt(new Date());
        Customer customer = null;
        try {
             customer = customerMongoRepository.save(requestCustomer);

        }catch (DuplicateKeyException exception){
            throw new RestClientResponseException("User Already Exists",
                    HttpStatus.CONFLICT.value(), "Same email & Pan should not exists",
                    null, null, null);
        }
        //TODO: email the customer.
       // emailCustomer(customer)
        return customer;
    }


    @Loggable(value=Loggable.WARN)
    @RequestMapping(path = "/verify" , method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "verify", nickname = "verify")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity verify(@RequestHeader(value = "Authorization") String authorization) {

        Customer customer = validateAuthorization(authorization);
        HttpStatus status = HttpStatus.OK;
        if(customer.isSuspended()){
            status = HttpStatus.UNAUTHORIZED;
        }
        // emailCustomer(customer)
        return new ResponseEntity(customer.getVerifyStatus(),status);
    }


    //Update User

    @Loggable(value=Loggable.WARN)
    @RequestMapping(path = "/update" , method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "update", nickname = "updateCustomer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Customer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public Customer update(@RequestHeader(value = "Authorization") String authorization,
                                   @RequestBody Customer requestCustomer) {

        Customer customer = validateAuthorization(authorization);

        //Donot overwrite id, email, password,company,isSuspend, verifyStatus.
        requestCustomer.setId(customer.getId());
        requestCustomer.setEmail(customer.getEmail());
        requestCustomer.setPasswd(customer.getPasswd());
        requestCustomer.setSuspended(customer.isSuspended());
        requestCustomer.setVerifyStatus(customer.getVerifyStatus());
        requestCustomer.setLastUpdated(new Date());

        // emailCustomer(customer)
        return customerMongoRepository.save(requestCustomer);
    }




    //Suspend User
    @Loggable(value=Loggable.WARN)
    @RequestMapping(path = "/suspend" , method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "suspend", nickname = "suspendUser")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Customer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public Customer suspend(@RequestHeader(value = "Authorization") String authorization
                                   ) {

        Customer customer = validateAuthorization(authorization);

        //Donot overwrite id, email, password,company,isSuspend, verifyStatus.

        customer.setSuspended(true);
        customer.setLastUpdated(new Date());

        // emailCustomer(customer)
        return customerMongoRepository.save(customer);
    }


    //Change Password User
    @Loggable(value=Loggable.WARN)
    @RequestMapping(path = "/changepwd" , method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "changepwd", nickname = "changePasswordCustomer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Customer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public Customer changepwd(@RequestHeader(value = "Authorization") String authorization,
                                @RequestBody Customer requestCustomer) {

        Customer customer = validateAuthorization(authorization);

        customer.setPasswd(requestCustomer.getPasswd());
        customer.setLastUpdated(new Date());
        customerMongoRepository.save(customer);
        //TODO: Send email for password change
        // emailCustomer(customer)
        return customer;
    }


    @RetryOnFailure(attempts = 2, delay = 100 ,types = ArithmeticException.class)
    public void process(String name) {

        System.out.println("Processing ...");
        int x = 10/0;

    }
}
