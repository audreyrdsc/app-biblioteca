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

    /*Teste para verificar o valor da home
    Verifica que a requisição GET para / retorna:
    Código HTTP 200 (OK)
    A view correta chamada "index"
    O atributo de modelo "menu" com valor "home"
     */
    @Test
    public void testPassarAtributoCorreto() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print()).andExpectAll(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("menu", "home"));
    }

    /*Teste para verificar o valor da home
    Esse teste deve falhar de propósito, pois o atributo "model" não existe no Model da view.
    Está sendo usado para verificar o que acontece quando um atributo incorreto é verificado.
    Serve como exemplo de teste negativo
    Vai falhar com erro de java.lang.AssertionError, indicando que o atributo "model" não está presente.
     */
    @Test
    public void testFalharAtributoIncorreto() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print()).andExpectAll(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("model", "home"));
    }

    /* Verificar se o atributo menu está presente
    Garante que o atributo "menu" está presente no Model, independentemente do valor.
    Útil quando não é necessário verificar o valor, apenas a existência do atributo.
     */
    @Test
    public void testVerificarAtributoPresente() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("menu"));
    }

    /* Verificar se a view existe mesmo sem atributos
    Verifica apenas se a view retornada é "index" e que a requisição retornou status HTTP 200.
🔍  Esse teste é útil para checagens mais simples, sem dependência de atributos no model.
     */
    @Test
    public void testSomenteViewCorreta() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }


}
