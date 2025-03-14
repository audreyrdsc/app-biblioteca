package com.unifap.biblioteca.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


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

  @Column(name = "nome", length = 100, nullable = false, unique = true)
	private String nome;

  @Column(name = "username", length = 100, nullable = false, unique = true)
  private String username;

	@Column(name = "cpf", columnDefinition = "char(11)", nullable = false, updatable = false)
	private String cpf;

	@Column(name = "cellprone", columnDefinition = "char(14)")
	private String cellphone;

	@Column(name = "telephone", columnDefinition = "char(13)")
	private String telephone;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Emprestimo> emprestimos;
}
