package com.unifap.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unifap.biblioteca.entities.Livro;
import com.unifap.biblioteca.exceptions.EntityInUseException;
import com.unifap.biblioteca.exceptions.EntityNotFoundException;
import com.unifap.biblioteca.repositories.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public Livro persist(Livro livro) {
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
