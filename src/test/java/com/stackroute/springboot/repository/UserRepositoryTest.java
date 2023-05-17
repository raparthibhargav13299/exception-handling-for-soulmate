package com.stackroute.springboot.repository;


import com.stackroute.springboot.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
//@SpringBootTest

    @DataJpaTest
class UserRepositoryTest {
    @Autowired
    private  UserRepository userRepository;
    @Test
    public void givenuser(){
        User user=new User(1,"john","stark",20,"male");
        userRepository.save(user);
        User user1=userRepository.findById(user.getId()).get();
        assertNotNull(user1);
        assertEquals(user1.getFirstname(),user1.getFirstname());
    }
    @Test
    public void givenAllUsersProfile() {
        User user = new User(1, "iron", "stark", 20, "male");
        User user1 = new User(2, "pro", "boy", 20, "male");
        userRepository.save(user);
        userRepository.save(user1);

        List<User> userList = (List<User>) userRepository.findAll();
        assertEquals("iron", userList.get(0).getFirstname());
        assertEquals("pro", userList.get(1).getFirstname());
        assertEquals(20, userList.get(0).getAge());
        assertEquals(20, userList.get(0).getAge());



    }


}