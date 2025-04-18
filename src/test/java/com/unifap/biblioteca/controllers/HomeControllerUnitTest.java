package com.unifap.biblioteca.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(HomeController.class)
public class HomeControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    /*  Acesso à rota
        Retorno do status HTTP 200
        Renderização da view index
        Presença do atributo menu com valor "home"
     */

    @Test
    @WithMockUser(username = "audrey", roles = {"USER"})
    public void testHomeComWebMvcTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("menu", "home"));
    }
}
