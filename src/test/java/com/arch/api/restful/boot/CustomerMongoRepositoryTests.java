package com.arch.api.restful.boot;

/**
 * Created by SRINIVASULU on 04/11/16.
 */


import static org.assertj.core.api.Assertions.*;

import java.util.Date;
import java.util.List;

import com.arch.api.restful.boot.beans.Customer;
import com.arch.api.restful.boot.db.repositories.CustomerMongoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerMongoRepositoryTests {

   @Autowired
    CustomerMongoRepository repository;

    //Customer dave, oliver, carter;

    @Before
    public void setUp() {

       // repository.deleteAll();

       /* dave = repository.save(new Customer("Dave", "Matthews"));
        oliver = repository.save(new Customer("Oliver August", "Matthews"));
        carter = repository.save(new Customer("Carter", "Beauford"));*/
    }

    @Test
    public void findAll() {

       List<Customer> list =  repository.findAll();

        /*Customer dave = repository.save(new Customer("Dave", "Matthews"));

        assertThat(dave.getId()).isNotNull();*/
    }
    @Test
    public void registerCustomer() {

        Customer dave = new Customer();
        dave.setPasswd("Sha1password");
        dave.setLastUpdated(new Date());
        dave.setCreatedAt(new Date());
        dave.setFirstName("Dave");
        dave.setLastName("LastNameDave");
        dave.setSuspended(false);
        dave.setAddress1("Address Line 1 # dasd$ , -dsfd ");
        dave.setAddress2("Address Line 2 # dasd$ , -dsfd ");
        dave.setAge(34);
        dave.setArea("Area 1");
        dave.setAssociationSinceFrom(2010);
        dave.setCity("Hyd");
        dave.setCompany("KloudPortal");
        dave.setEmail("dave@email.com");
        dave.setMobile1("9239239239");
        dave.setMobile2("23432423243");
        dave.setNickname("Dave Nick Name");
        dave.setPan("TG234432K");
        dave.setType("TaxPayee");
        dave.setYearOfBirth(1955);
        dave.setVerifyStatus("Just Registering");

        dave =   repository.save(dave);

        assertThat(dave.getId()).isNotNull();
    }

    @Test
    public void findsByLastName() {

        List<Customer> customers = repository.findByEmailAndPasswd("dave@email.com","Sha1password");

        customers.get(0).setPasswd("Sha1password11");
        repository.save(customers.get(0));
     //   assertThat(result).hasSize(1).extracting("firstName").contains("Carter");
    }
/*
    @Test
    public void findsByExample() {

        Customer probe = new Customer(null, "Matthews");

        List<Customer> result = repository.findAll();

        assertThat(result).hasSize(3).extracting("firstName").contains("Dave", "Oliver August");
    }*/
}