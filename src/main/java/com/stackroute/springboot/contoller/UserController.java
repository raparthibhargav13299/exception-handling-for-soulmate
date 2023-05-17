package com.stackroute.springboot.contoller;

import com.stackroute.springboot.exception.UserAlreadyExistsException;
import com.stackroute.springboot.exception.UserNotFoundException;
import com.stackroute.springboot.model.User;
import com.stackroute.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {
private UserService userService;
@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>>getAllUsers() throws UserNotFoundException, Exception {
        return new ResponseEntity<List<User>>((List<User>)userService.getAllUsers(),HttpStatus.OK);

    }
    //    @RequestMapping(value ="/teams/{firstname}", method = RequestMethod.GET)
    @GetMapping("/profile/gender")
    public ResponseEntity <List<User>> SearchUserByGender(String gender) throws UserNotFoundException, Exception {
        return new ResponseEntity<List<User>>((List<User>)userService.SearchUserByGender(gender),HttpStatus.OK);


    }

    @GetMapping("/post/firstname")
    public ResponseEntity <List<User>> SearchUserByName(String firstname) throws UserNotFoundException, Exception {
        return new ResponseEntity<List<User>>((List<User>)userService.SearchUserByName(firstname),HttpStatus.OK);


    }


    @GetMapping("/user/age")
    public ResponseEntity <List<User>> SearchUserByAge(int age) throws UserNotFoundException, Exception {
        return new ResponseEntity<List<User>>((List<User>)userService.SearchUserByage(age),HttpStatus.OK);


    }

@PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws UserAlreadyExistsException, Exception {
    User savedUser=userService.saveUser(user);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

}

    @PutMapping("/userss/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User user) throws Exception, UserNotFoundException {
        user.setId(id);
        return ResponseEntity.ok().body(this.userService.updateUser(user));
    }
    @DeleteMapping("/usersss/{id}")
    public HttpStatus deleteUser(@PathVariable int id) throws Exception, UserNotFoundException {
        this.userService.deleteUser(id);
        return HttpStatus.OK;
    }




}
