package com.unifap.biblioteca.repositories;


import com.unifap.biblioteca.entities.Livro;
import com.unifap.biblioteca.entities.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{

    @Query("SELECT m.livro FROM Movimentacao m WHERE m.cliente.id = :clienteId AND m.dataDevolucao IS NULL")
    List<Livro> findLivrosEmprestadosAtivosPorCliente(@Param("clienteId") Long clienteId);

    @Query("SELECT m FROM Movimentacao m WHERE m.livro.id = :livroId AND m.dataDevolucao IS NULL")
    Optional<Movimentacao> findByLivroIdAndDataDevolucaoIsNull(@Param("livroId") Long livroId);

    @Query("SELECT m FROM Movimentacao m WHERE m.livro.id = :livroId AND m.dataDevolucao IS NOT NULL")
    Optional<Movimentacao> findByLivroIdAndDataDevolucaoIsNotNull(@Param("livroId") Long livroId);


}
