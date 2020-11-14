package com.example.awsbook.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HelloController.class)
class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        // given
        String hello = "hello";

        // when
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));

    }

    @Test
    public void getHelloDto() throws Exception {
        // given
        String name = "hello";
        int amount = 1000;

        // when
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.amount").value(amount));

    }
}