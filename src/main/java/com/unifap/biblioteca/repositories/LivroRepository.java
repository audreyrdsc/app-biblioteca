package com.unifap.biblioteca.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unifap.biblioteca.entities.Livro;



@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
