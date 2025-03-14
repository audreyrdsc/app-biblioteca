package com.unifap.biblioteca.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unifap.biblioteca.entities.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
