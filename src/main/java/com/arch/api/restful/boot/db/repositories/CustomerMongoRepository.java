package com.arch.api.restful.boot.db.repositories;

/**
 * Created by SRINIVASULU on 04/11/16.
 */
import com.arch.api.restful.boot.db.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;



public interface CustomerMongoRepository extends MongoRepository<Customer, String> {


    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);


    @Query("{ 'name' : ?0 }")
    List<Customer> findUsersByName(String name);

    @Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")
    List<Customer> findUsersByAgeBetween(int ageGT, int ageLT);

    @Query("{ 'name' : { $regex: ?0 } }")
    List<Customer> findUsersByRegexpName(String regexp);

    List<Customer> findByName(String name);

    List<Customer> findByNameLikeOrderByAgeAsc(String name);

    List<Customer> findByAgeBetween(int ageGT, int ageLT);

    List<Customer> findByNameStartingWith(String regexp);

    List<Customer> findByNameEndingWith(String regexp);

}