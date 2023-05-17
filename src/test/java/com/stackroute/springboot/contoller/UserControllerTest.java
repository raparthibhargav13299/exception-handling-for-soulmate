package com.stackroute.springboot.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.springboot.exception.UserAlreadyExistsException;
import com.stackroute.springboot.exception.UserNotFoundException;
import com.stackroute.springboot.model.User;
import com.stackroute.springboot.service.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;




import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import java.util.ArrayList;
import java.util.List;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Mock
    private UserService userService;
    private User user;
    private User user2;
    private List<User> userList;


    @InjectMocks
    private UserController userController;


    @BeforeEach
    public void setUp() {
        user = new User(1, "robert", "down", 21, "Male");
        user2 = new User(2, "john", "cena", 21, "Male");
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void givenUserToSaveShouldReturnSavedUser() throws UserAlreadyExistsException, Exception {
        when(userService.saveUser(any())).thenReturn(user);
        mockMvc.perform(post("/api/v1" +
                "/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isCreated());

        verify(userService, times(1)).saveUser(any());
    }

    @Test
    public void getAllUsersThenShouldReturnListOfUsers() throws UserNotFoundException, Exception {
        when(userService.getAllUsers()).thenReturn(userList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).getAllUsers();
    }




    private static String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }



//    @Test
//    public void getAllUsersThenShouldReturnListOfUsers() throws Exception{
//        when(userService.getAllUsers()).thenReturn(userList);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//                .andDo(MockMvcResultHandlers.print());
//        verify(userService,times(1)).getAllUsers();
//    }

//       verify(userService, times(1)).saveUser(any());
    }

