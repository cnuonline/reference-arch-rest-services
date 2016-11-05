package com.arch.api.restful.boot;

/**
 * Created by SRINIVASULU on 04/11/16.
 */


import static org.assertj.core.api.Assertions.*;

import java.util.List;

import com.arch.api.restful.boot.db.Customer;
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

    Customer dave, oliver, carter;

    @Before
    public void setUp() {

        repository.deleteAll();

        dave = repository.save(new Customer("Dave", "Matthews"));
        oliver = repository.save(new Customer("Oliver August", "Matthews"));
        carter = repository.save(new Customer("Carter", "Beauford"));
    }

    @Test
    public void setsIdOnSave() {

        Customer dave = repository.save(new Customer("Dave", "Matthews"));

        assertThat(dave.getId()).isNotNull();
    }

    @Test
    public void findsByLastName() {

        List<Customer> result = repository.findByLastName("Beauford");

        assertThat(result).hasSize(1).extracting("firstName").contains("Carter");
    }

    @Test
    public void findsByExample() {

        Customer probe = new Customer(null, "Matthews");

        List<Customer> result = repository.findAll();

        assertThat(result).hasSize(3).extracting("firstName").contains("Dave", "Oliver August");
    }
}