package com.unifap.biblioteca.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClienteUnitTest {

    private Validator validator;

    @Autowired
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    @BeforeEach
    public void setUp() {
        // Inicializando o validador do Spring
        validator = localValidatorFactoryBean;
    }

    @Test
    public void testValidCliente() {
        // Criando um cliente com dados válidos
        Cliente cliente = new Cliente(
                null,
                "24757689080", // sem pontos e traços, pois será formatado automaticamente no @PrePersist
                "João Silva",
                new java.util.Date(),
                "99999-9999",
                "3333-3333",
                "joao@exemplo.com",
                null,
                LocalDateTime.now(),   // createdAt
                "audrey",              // createdBy
                LocalDateTime.now(),   // updatedAt
                "audrey"               // updatedBy
        );

        // Realizando a validação
        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(cliente, "cliente");
        validator.validate(cliente, bindingResult);

        // Imprime os erros se houver
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println("Erro: " + error));
        }

        // Verificando que não existem erros de validação
        assertFalse(bindingResult.hasErrors(), "O cliente não deve ter erros de validação.");
    }


    @Test
    public void testInvalidCpf() {
        // Criando um cliente com CPF inválido
        Cliente cliente = new Cliente(
                null, "123.456.789.000", "João Silva", new java.util.Date(), "99999-9999", "3333-3333", "joao@exemplo.com", null, null, null, null, null
        );

        // Realizando a validação
        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(cliente, "cliente");
        validator.validate(cliente, bindingResult);

        // Verificando que há erro de validação para CPF
        assertTrue(bindingResult.hasErrors(), "O cliente deve ter erros de validação devido ao CPF inválido.");
        assertEquals(1, bindingResult.getFieldErrorCount("cpf"), "Deve haver 1 erro de validação para o CPF.");
    }

    @Test
    public void testNomeTooShort() {
        // Criando um cliente com nome muito curto
        Cliente cliente = new Cliente(
                null, "123.456.789-00", "Jo", new java.util.Date(), "99999-9999", "3333-3333", "jo@exemplo.com", null, null, null, null, null
        );

        // Realizando a validação
        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(cliente, "cliente");
        validator.validate(cliente, bindingResult);

        // Verificando que há erro de validação para o nome
        assertTrue(bindingResult.hasErrors(), "O cliente deve ter erros de validação devido ao nome muito curto.");
        assertEquals(1, bindingResult.getFieldErrorCount("nome"), "Deve haver 1 erro de validação para o nome.");
    }

    @Test
    public void testEmailRequired() {
        // Criando um cliente sem email
        Cliente cliente = new Cliente(
                null, "123.456.789-00", "João Silva", new java.util.Date(), "99999-9999", "3333-3333", "", null, null, null, null, null
        );

        // Realizando a validação
        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(cliente, "cliente");
        validator.validate(cliente, bindingResult);

        // Verificando que há erro de validação para o email
        assertTrue(bindingResult.hasErrors(), "O cliente deve ter erro de validação devido à falta de email.");
        assertEquals(1, bindingResult.getFieldErrorCount("email"), "Deve haver 1 erro de validação para o email.");
    }
}
