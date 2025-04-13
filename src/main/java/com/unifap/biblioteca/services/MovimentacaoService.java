package com.unifap.biblioteca.services;

import com.unifap.biblioteca.entities.Cliente;
import com.unifap.biblioteca.entities.Livro;
import com.unifap.biblioteca.entities.Movimentacao;
import com.unifap.biblioteca.exceptions.EntityNotFoundException;
import com.unifap.biblioteca.repositories.ClienteRepository;
import com.unifap.biblioteca.repositories.LivroRepository;
import com.unifap.biblioteca.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private AuditingService auditingService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public Movimentacao devolver(Long idLivro, Long idCliente) {
        String userLogged = auditingService.getCurrentAuditor().orElse("Sistema");
        Movimentacao movimentacao = movimentacaoRepository.findByLivroIdAndDataDevolucaoIsNull(idLivro)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum empréstimo ativo encontrado para o livro " + idLivro));

        //Operações para devolução do livro
        movimentacao.setUpdatedBy(userLogged);
        movimentacao.setDataDevolucao(LocalDateTime.now());
        movimentacao.getLivro().setDisponivel(true);
        return movimentacaoRepository.save(movimentacao);
    }


    @Transactional
    public Movimentacao emprestar(Long idLivro, Long idCliente) {
        String userLogged = auditingService.getCurrentAuditor().orElse("Sistema"); //////
        Livro livro = livroRepository.findById(idLivro)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado: " + idLivro));

        if (!livro.isDisponivel()) {
            throw new IllegalStateException("O livro não está disponível para empréstimo.");
        }

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado: " + idCliente));

        //Operações para emprestar o livro
        Movimentacao movimentacao = new Movimentacao();
            movimentacao.setCreatedBy(userLogged);
            movimentacao.setUpdatedBy(userLogged);
            movimentacao.setLivro(livro);
            movimentacao.setCliente(cliente);
            movimentacao.setDataEmprestimo(LocalDateTime.now());
            movimentacao.setDataTermino(LocalDateTime.now().plusDays(15));
            movimentacao.getLivro().setDisponivel(false);

        return movimentacaoRepository.save(movimentacao);
    }

}
