package com.stackroute.springboot.repository;

import com.stackroute.springboot.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {


    @Query("FROM User WHERE gender =:gender")
    List<User> getAllUsersByGender(@Param("gender") String gender);


    @Query("FROM User WHERE firstname =:firstname")
    List<User> getAllUsersByName(@Param("firstname") String firstname);


    @Query("FROM User WHERE age =:age")
    List<User> getAllUsersByAge(@Param("age") int age);



}
