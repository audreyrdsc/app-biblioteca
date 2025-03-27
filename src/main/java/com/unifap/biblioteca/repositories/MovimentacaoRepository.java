package com.unifap.biblioteca.repositories;


import com.unifap.biblioteca.entities.Cliente;
import com.unifap.biblioteca.entities.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{

}
