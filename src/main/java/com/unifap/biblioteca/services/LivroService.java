package com.unifap.biblioteca.services;

import com.unifap.biblioteca.entities.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unifap.biblioteca.exceptions.EntityInUseException;
import com.unifap.biblioteca.exceptions.EntityNotFoundException;
import com.unifap.biblioteca.repositories.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AuditingService auditingService;

   
    public Livro persist(Livro livro) {
        String userLogged = auditingService.getCurrentAuditor().orElse("Sistema");
        
        if (livroRepository.existsByIsbnAndIdNot(livro.getIsbn(), livro.getId())) {
            throw new IllegalArgumentException("Este ISBN já está cadastrado para outro livro.");
        }
        return livroRepository.save(livro);
    }

    public Livro findOrFail(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Livro %d não encontrado", id)));
    }

    @Transactional
    public void delete(Livro livro) {
        try {
            livroRepository.deleteById(livro.getId());
            livroRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Livro %d em uso", livro.getId()));
        }
    }
}
