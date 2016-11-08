package com.arch.api.restful.boot.db.repositories;

/**
 * Created by SRINIVASULU on 04/11/16.
 */
import com.arch.api.restful.boot.beans.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;



public interface CustomerMongoRepository extends MongoRepository<Customer, String> {


  //  public Customer findByFirstName(String firstName);
  //  public List<Customer> findByLastName(String lastName);


    @Query("{ 'nickname' : ?0 }")
    List<Customer> findUsersByNickname(String nickname);

  /*  @Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")
    List<Customer> findUsersByAgeBetween(int ageGT, int ageLT);*/

    @Query("{ 'nickname' : { $regex: ?0 } }")
    List<Customer> findUsersByRegexpNickname(String regexp);

    //Login
    List<Customer> findByEmailAndPasswd(String email, String passwd);

    //Get Profile , Verify Status
    public Customer findByEmail(String email);






   // List<Customer> findByNickname(String nickname);

    //List<Customer> findByNameLikeOrderByAgeAsc(String name);

   /* List<Customer> findByAgeBetween(int ageGT, int ageLT);

    List<Customer> findByNicknameStartingWith(String regexp);

    List<Customer> findByNicknameEndingWith(String regexp);*/



}