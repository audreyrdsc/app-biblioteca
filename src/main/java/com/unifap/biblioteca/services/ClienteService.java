package com.unifap.biblioteca.services;

import com.unifap.biblioteca.entities.Cliente;
import com.unifap.biblioteca.exceptions.EntityInUseException;
import com.unifap.biblioteca.exceptions.EntityNotFoundException;
import com.unifap.biblioteca.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private AuditingService auditingService;

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente persist(Cliente cliente) {
        String userLogged = auditingService.getCurrentAuditor().orElse("Sistema");

        if (cliente.getId() == null) { // Cliente novo
            cliente.setCreatedBy(userLogged);
        }

        cliente.setUpdatedBy(userLogged);
        return clienteRepository.save(cliente);
    }


    public Cliente findOrFail(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Cliente %d não encontrado", id)));
    }

    @Transactional
    public void delete(Cliente cliente) {
        try {
            clienteRepository.deleteById(cliente.getId());
            clienteRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Cliente %d em uso", cliente.getId()));
        }
    }

}
