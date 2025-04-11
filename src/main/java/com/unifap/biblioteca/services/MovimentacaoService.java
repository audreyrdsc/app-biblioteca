package com.unifap.biblioteca.services;

import com.unifap.biblioteca.entities.Livro;
import com.unifap.biblioteca.entities.Movimentacao;
import com.unifap.biblioteca.exceptions.EntityInUseException;
import com.unifap.biblioteca.exceptions.EntityNotFoundException;
import com.unifap.biblioteca.repositories.LivroRepository;
import com.unifap.biblioteca.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private AuditingService auditingService;

    @Autowired
    private LivroRepository livroRepository;

    public Movimentacao persist(Long idLivro) {
        Movimentacao movimentacao = movimentacaoRepository.findByLivroIdAndDataDevolucaoIsNull(idLivro)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum empréstimo ativo encontrado para o livro " + idLivro));
        movimentacao.setDataDevolucao(LocalDate.now());
        movimentacao.getLivro().setDisponivel(true);
        return movimentacaoRepository.save(movimentacao);
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
