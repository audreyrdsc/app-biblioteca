package com.unifap.biblioteca.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
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
    @Getter
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "CPF é obrigatório")
	@CPF(message = "CPF é inválido")
	@Column(name = "cpf", columnDefinition = "char(14)", nullable = false, updatable = false)
	private String cpf;

	@NotBlank(message = "Nome é obrigatório")
	@Length(min = 3, max = 100, message = "Nome deve conter entre 3 e 50 caracteres")
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Nascimento é obrigatório")
	@Column(name = "nascimento", columnDefinition = "date", nullable = false)
	private Date nascimento;

	@Column(name = "cellphone", columnDefinition = "char(15)")
	private String cellphone;

	@Column(name = "telephone", columnDefinition = "char(14)")
	private String telephone;

	@NotBlank(message = "Email é obrigatório")
	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Emprestimo> emprestimos;

	//Este Getter deveria ser feito pelo LOMBOK mas está dando erro de dependência
	//public Long getId() {
	//	return id;
	//}

	@PostLoad
	private void postLoad() {
		this.cpf = this.cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
	}

	@PrePersist @PreUpdate
	private void prePersisPreUpdate() {
		this.cpf = this.cpf.replaceAll("\\.|-", "");
	}

}
