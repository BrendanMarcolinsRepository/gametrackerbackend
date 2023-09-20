package com.api.gamerating.controllers;

import com.api.gamerating.repository.UserRepository;
import jakarta.validation.constraints.NotEmpty;
import net.minidev.json.JSONObject;
import org.assertj.core.internal.Arrays;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
public class AuthControllerTest {

    private static MockHttpServletRequest request;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;



    @Test
    public void userLoginByUsernameExistent() throws Exception{

        JSONObject body = new JSONObject();
        body.put("password","p");
        body.put("username","fullmetal");


        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(String.valueOf(body)))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

    }


    @Test
    public void userLoginByUsernameNonExistent() throws Exception{

        JSONObject body = new JSONObject();
        body.put("password","k");
        body.put("username","fullmetal");

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(String.valueOf(body)))
                .andDo(print())
                .andExpect(status().is4xxClientError()).andReturn();


    }

    @Test
    public void userRegisterAuthAndHtmlRequest() throws Exception{

        JSONArray rolesArray = new JSONArray();
        rolesArray.put("USER_ROLE");


        JSONObject body = new JSONObject();
        body.put("password","k");
        body.put("firstName","ell");
        body.put("lastName","marcolin");
        body.put("username","rando");
        body.put("email","em@gmail.com");

        body.put("roles", rolesArray.get(0));


        assertFalse(body.isEmpty());

        assertNotNull(body.isEmpty());



        MvcResult temp = mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .header("Authorization", "Bearer" )
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(String.valueOf(body)))
                .andReturn();

        MockHttpServletRequest result = temp.getRequest();
        assertNotNull( result.getSession());
        System.out.println("Session ---- " + result.getAuthType());


    }

    @Test
    public void EmptyRegisterAuthAndHtmlRequest() throws Exception{

        JSONObject body = new JSONObject();

        assertTrue(body.isEmpty());

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(String.valueOf(body)))
                .andExpect(content().string(Matchers.blankOrNullString()))
                .andDo(print())
                .andExpect(status().is4xxClientError()).andReturn();


    }
}
