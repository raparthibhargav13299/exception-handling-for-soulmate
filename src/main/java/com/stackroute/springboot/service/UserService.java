package com.stackroute.springboot.service;

import com.stackroute.springboot.exception.UserAlreadyExistsException;
import com.stackroute.springboot.exception.UserNotFoundException;
import com.stackroute.springboot.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user) throws UserAlreadyExistsException,Exception;
    List<User> getAllUsers() throws UserNotFoundException,Exception;
    public void deleteUser(int id) throws UserNotFoundException,Exception;
    User updateUser(User user) throws UserNotFoundException,Exception;


    List<User> SearchUserByGender(String gender) throws UserNotFoundException,Exception;
    List<User> SearchUserByName(String firstname)   throws UserNotFoundException,Exception;
    List<User> SearchUserByAge(int age) throws UserNotFoundException,Exception;

}
