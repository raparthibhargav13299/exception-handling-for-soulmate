package com.stackroute.springboot.service;

import com.stackroute.springboot.exception.UserAlreadyExistsException;
import com.stackroute.springboot.exception.UserNotFoundException;
import com.stackroute.springboot.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.apache.logging.log4j.ThreadContext.isEmpty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.stackroute.springboot.model.User;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private User user, user1;
    private List<User> userList1;

    @InjectMocks
    private UserServiceImpl userService;



    @Test
    public void givenBlogToSaveThenShouldReturnSavedBlog() throws UserAlreadyExistsException, Exception {
        User user = new User(1, "hellboy", "spidey", 21, "Male");
        when(userRepository.save(any())).thenReturn(user);
        assertEquals(user, userService.saveUser(user));
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void givenAllUserTest() throws UserNotFoundException, Exception {
        User user = new User(1, "hellboy", "spidey", 21, "Male");
        User user1 = new User(2, "ironman", "stark", 200, "Male");
        userRepository.save(user);
        userRepository.save(user1);
//        userRepository.deleteById(2);
        when(userRepository.findAll()).thenReturn(userList1);
        List<User> userList = userService.getAllUsers();
        assertEquals(userList1, userList);
        verify(userRepository, times(1)).save(user);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void delTest() throws UserNotFoundException, Exception {
        User user = new User(1, "hellboy", "spidey", 21, "Male");
        User user1 = new User(2, "ironman", "stark", 200, "Male");
        userRepository.save(user);
        userRepository.save(user1);
        userRepository.deleteById(2);
        when(userRepository.findAll()).thenReturn(userList1);
        List<User> userList = userService.getAllUsers();
        assertEquals(userList1, userList);

        verify(userRepository, times(1)).deleteById(2);

    }


//    @Test
//    public void updTest() throws UserNotFoundException, Exception {
//        User user = new User(1, "hellboy", "spidey", 21, "Male");
////        User user1 = new User(2, "ironman", "stark", 200, "Male");
////        userRepository.save(user);
////        userRepository.save(user1);
////        userRepository.deleteById(2);
//
//        when(userRepository.findById(user.getId()).thenReturn(userList1);
//        List<User> userList = userService.getAllUsers();
//        assertEquals(userList1, userList);
//
//        verify(userRepository, times(1)).deleteById(2);
//
//    }
//    @Test
//    public void searchbyname() throws UserNotFoundException,Exception{
//        User user = new User(1, "hellboy", "spidey", 21, "Male");
//        String name="hellboy";
////        User user1 = (User) userRepository.getAllUsersByName("hellboy");
//        when(userRepository.findById(user.getId()).!isEmpty();
//        assertEquals(user1.getFirstname(),name);
//    }









}