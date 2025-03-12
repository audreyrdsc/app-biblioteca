package com.unifap.biblioteca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente {
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
	
  @Column(name = "nome", lenght = 100, nullable = false, unique = true)
	private String nome;
    
  @Column(name = "username", length = 100, nullable = false, unique = true)
  private String username;
	
	@Column(columnDefinition = "char(11)", nullable = false, updatable = false)
	private String cpf;

	@Column(columnDefinition = "char(14)")
	private String cellphone;

	@Column(columnDefinition = "char(13)")
	private String telephone;
}
