package com.unifap.biblioteca.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unifap.biblioteca.entities.Cliente;



@Repository
public interface ClienteRepository extends JpaRepository<Cliente, long>{

}
