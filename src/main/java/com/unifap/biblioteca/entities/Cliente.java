package com.unifap.biblioteca.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cpf", columnDefinition = "char(14)", nullable = false, updatable = false)
	private String cpf;

	@Column(name = "nome", length = 100, nullable = false, unique = true)
	private String nome;

	//@Column(name = "username", length = 100, nullable = false, unique = true)
	//private String username;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Nascimento é obrigatório")
	@Column(name = "nascimento", columnDefinition = "date", nullable = false)
	private Date nascimento;

	@Column(name = "cellphone", columnDefinition = "char(15)")
	private String cellphone;

	@Column(name = "telephone", columnDefinition = "char(14)")
	private String telephone;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Emprestimo> emprestimos;

	public Long getId() {
		return this.id;
	}

}
