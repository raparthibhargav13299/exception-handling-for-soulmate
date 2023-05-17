package com.stackroute.springboot.service;

import com.stackroute.springboot.exception.UserAlreadyExistsException;
import com.stackroute.springboot.exception.UserNotFoundException;
import com.stackroute.springboot.model.User;
import com.stackroute.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException,Exception {
        if(userRepository.existsById(user.getId())){
            throw new UserAlreadyExistsException();

        }
        User savedUser=userRepository.save(user);

        return savedUser;
    }


    @Override
    public List<User> getAllUsers() throws UserNotFoundException,Exception {
        List<User> userDB= (List<User>) this.userRepository.findAll();
        if(userDB!=null){
            throw new UserNotFoundException();
        }
        return (List<User>) userDB;
    }

    @Override
    public User updateUser(User user)throws UserNotFoundException ,Exception{
        Optional<User> userDB =this.userRepository.findById(user.getId());
        if(userDB.isPresent()) {
            User userUpdate = userDB.get();
            userUpdate.setId(user.getId());
            userUpdate.setFirstname(user.getFirstname());
            userUpdate.setLastname(user.getLastname());
            userUpdate.setGender(user.getGender());

            userUpdate.setAge(user.getAge());
            userRepository.save(userUpdate);
            return userUpdate;
        }else{
            throw new UserNotFoundException();
        }

    }

    @Override
    public List<User> SearchUserByGender(String gender)throws UserNotFoundException,Exception {
        List<User> userDB= (List<User>) this.userRepository.getAllUsersByGender(gender);
        if(userDB.isEmpty()) {
            throw  new UserNotFoundException();
        }
        else
        {

            return userRepository.getAllUsersByName(gender);
        }
    }



    @Override
    public List<User> SearchUserByName(String firstname)throws UserNotFoundException ,Exception{
        List<User> userDB=this.userRepository.getAllUsersByName(firstname);
        if(userDB.isEmpty()) {
            throw  new UserNotFoundException();
        }
        else{
        return  userRepository.getAllUsersByName(firstname);}
    }


    @Override
    public List<User> SearchUserByAge(int age)throws UserNotFoundException ,Exception{

        List<User> userDB= (List<User>) this.userRepository.getAllUsersByage(age);
        if(userDB.isEmpty()) {
            throw  new UserNotFoundException();
        }
        else{
        return  userRepository.getAllUsersByage(age);}
    }


    @Override
    public void deleteUser(int id) throws UserNotFoundException ,Exception{
//        userRepository.findById(id).ifPresent(User-> userRepository.delete(User));
        Optional<User> userDB=this.userRepository.findById(id);
        if(userDB.isPresent()){
            this.userRepository.delete(userDB.get());
        }else{
           throw  new UserNotFoundException();
        }
    }



}
