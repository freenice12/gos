package com.example.demo.members;

import com.example.demo.DemoApplicationTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MembershipControllerITest extends DemoApplicationTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void test_get_memberships_should_return_empty() throws Exception {
        ResultActions resultActions = mvc
                .perform(get("/memberships")
//                        .with(httpBasic("id", "pwd"))
                )
                .andExpect(status().isOk());

        resultActions.andDo(print());
    }

    @Test
    public void test_post_memberships_create_success() throws Exception {
        MembershipDTO.Create create = new MembershipDTO.Create();
        create.setEmail("tester@gmail.com");
        create.setPassword("test");
        create.setAdmin(true);

        ResultActions result = mvc.perform(post("/memberships")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(create)));

        result.andDo(print());
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id", is(1)));
        result.andExpect(jsonPath("$.email", is("tester@gmail.com")));
        result.andExpect(jsonPath("$.password", is("test")));
    }

    @Test
    public void test_post_memberships_create_fail_empty_password() throws Exception {
        MembershipDTO.Create create = new MembershipDTO.Create();
        create.setEmail("tester1@gmail.com");
        String emptyPassword = null;
        create.setPassword(emptyPassword);

        System.out.println("=========================");
        System.out.println(objectMapper.writeValueAsString(create));
        ResultActions result = mvc.perform(post("/memberships")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(create)));

        result.andDo(print());
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.message", is("잘못된 요청입니다. (password : may not be empty)")));
        result.andExpect(jsonPath("$.code", is("bad.request")));
    }

    @Test
    public void test_post_memberships_create_fail_short_password() throws Exception {
        MembershipDTO.Create create = new MembershipDTO.Create();
        create.setEmail("tester@gmail.com");
        String shortPassword = "123";
        create.setPassword(shortPassword);

        ResultActions result = mvc.perform(post("/memberships")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(create)));

        result.andDo(print());
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.message", is("잘못된 요청입니다. (password : size must be between 4 and 30)")));
        result.andExpect(jsonPath("$.code", is("bad.request")));
    }

}