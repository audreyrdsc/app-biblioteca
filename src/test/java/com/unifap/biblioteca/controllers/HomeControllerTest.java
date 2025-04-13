package com.unifap.biblioteca.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class HomeControllerTest {

    //Suporte ao teste MVC
    private MockMvc mockMvc;

    //Injeção de dependência com a classe HomeController
    @Autowired
    private HomeController homeController;

    //Configuração antes de cada teste
    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(homeController)
                .build();
    }

    //Teste para verificar o valor da home
    @Test
    public void testPassarAtributoCorreto() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print()).andExpectAll(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("menu", "home"));
    }

    //Teste para verificar o valor da home
    @Test
    public void testFalharAtributoIncorreto() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print()).andExpectAll(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("model", "home"));
    }

}
