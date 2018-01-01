package com.example.demo.members;

import com.example.demo.DemoApplicationTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @WithMockUser
    public void test_get_memberships_should_return_empty() throws Exception {
        ResultActions resultActions = mvc
                .perform(get("/memberships").with(httpBasic("id", "pwd")))
                .andExpect(status().isOk());

        resultActions.andDo(print());
    }

    @Test
    public void test_post_memberships() throws Exception {
        MembershipDTO.Create create = new MembershipDTO.Create();
        create.setEmail("tester@gmail.com");
        create.setPassword("test");
        create.setAdmin(true);

        ResultActions result = mvc.perform(post("/memberships")
                .with(httpBasic("id", "pwd"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(create)));

        result.andDo(print());
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id", is(1)));
        result.andExpect(jsonPath("$.email", is("tester@gmail.com")));
        result.andExpect(jsonPath("$.password", is("test")));
    }

}