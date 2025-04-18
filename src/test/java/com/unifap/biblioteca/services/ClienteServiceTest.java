package com.unifap.biblioteca.services;

import com.unifap.biblioteca.entities.Cliente;
import com.unifap.biblioteca.exceptions.EntityInUseException;
import com.unifap.biblioteca.exceptions.EntityNotFoundException;
import com.unifap.biblioteca.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private AuditingService auditingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Testa o método persist para novo cliente (sem ID).
     * Verifica se os campos de auditoria são preenchidos corretamente.
     */
    @Test
    void testPersistNovoCliente() {
        Cliente cliente = new Cliente(
                null, "12345678900", "João Silva", new Date(), "99999-9999", "3333-3333",
                "joao@email.com", null, null, null, null, null
        );

        when(auditingService.getCurrentAuditor()).thenReturn(Optional.of("audrey"));
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente salvo = clienteService.persist(cliente);

        assertEquals("audrey", salvo.getCreatedBy());
        assertEquals("audrey", salvo.getUpdatedBy());
    }

    /**
     * Testa o método persist para cliente existente (com ID).
     * Verifica que apenas updatedBy é alterado.
     */
    @Test
    void testPersistClienteExistente() {
        Cliente cliente = new Cliente(
                1L, "12345678900", "João Silva", new Date(), "99999-9999", "3333-3333",
                "joao@email.com", null, null, "audrey", null, null
        );

        when(auditingService.getCurrentAuditor()).thenReturn(Optional.of("audrey"));
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente salvo = clienteService.persist(cliente);

        // createdBy não muda
        assertEquals("audrey", salvo.getCreatedBy());
        // updatedBy é atualizado
        assertEquals("audrey", salvo.getUpdatedBy());
    }

    /**
     * Testa o método findOrFail quando o cliente existe.
     */
    @Test
    void testFindOrFailExistente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente encontrado = clienteService.findOrFail(1L);

        assertEquals(1L, encontrado.getId());
    }

    /**
     * Testa o método findOrFail quando o cliente não existe.
     * Espera exceção EntityNotFoundException.
     */
    @Test
    void testFindOrFailNaoExistente() {
        when(clienteRepository.findById(2L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> clienteService.findOrFail(2L)
        );

        assertEquals("Cliente 2 não encontrado", exception.getMessage());
    }

    /**
     * Testa o método delete com sucesso.
     */
    @Test
    void testDeleteComSucesso() {
        Cliente cliente = new Cliente();
        cliente.setId(3L);

        clienteService.delete(cliente);

        verify(clienteRepository, times(1)).deleteById(3L);
        verify(clienteRepository, times(1)).flush();
    }

    /**
     * Testa o método delete quando o cliente está em uso.
     * Espera exceção EntityInUseException.
     */
    @Test
    void testDeleteEmUso() {
        Cliente cliente = new Cliente();
        cliente.setId(4L);

        doThrow(DataIntegrityViolationException.class)
                .when(clienteRepository).deleteById(4L);

        EntityInUseException exception = assertThrows(
                EntityInUseException.class,
                () -> clienteService.delete(cliente)
        );

        assertEquals("Cliente 4 em uso", exception.getMessage());
    }
}
