package uutiset.wepauutiset.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static junit.framework.TestCase.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest


public class NewsControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @Autowired
    private Filter springSecurityFilterChain;


    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webAppContext)
                .addFilters(springSecurityFilterChain)
                .build();

    }


    @Test
    public void login() throws Exception {

        mockMvc.perform(post("/registration")
                .param("name", "Mikko")
                .param("password", "Banaani123"))
                .andExpect(status().is3xxRedirection());

        MvcResult res = mockMvc.perform(post("/login").
                param("username", "Mikko")
                .param("password", "Banaani123"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

    }


    @Test

    public void addCategoryTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/addCategory")
                .param("name", "Ulkomaat"))
                .andExpect(MockMvcResultMatchers.flash().attribute("messages", "Category added!"));
    }

    @Test
    public void postNews() throws Exception {
        this.login();

        MvcResult res = mockMvc.perform(fileUpload("/addNews")
                .param("head", "uusi uutinen")
                .param("content", "Uusi contentUusi " +
                        "contentUusi contentUusi contentUusi " +
                        "contentUusi content")
                .param("ingress", "Ingressi uutiseen on tässä!")
                .param("writer", "1")
                .param("category", "1"))
                .andReturn();


    }


}
