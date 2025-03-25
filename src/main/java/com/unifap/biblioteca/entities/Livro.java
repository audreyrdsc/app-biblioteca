package com.unifap.biblioteca.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ISBN é obrigatório")
    @Column(name = "isbn", columnDefinition = "char(15)", nullable = false, unique = true, updatable = false)
    private String isbn;

    @NotBlank(message = "Título é obrigatório")
    @Length(min = 3, max = 200, message = "Título deve conter entre 3 e 200 caracteres")
    @Column(name = "titulo", nullable = false, unique = true, length = 200)
    private String titulo;

    @NotBlank(message = "Autores é obrigatório")
    @Length(min = 3, max = 200, message = "Autores deve conter entre 3 e 200 caracteres")
    @Column(name = "autores", nullable = false, length = 100)
    private String autores;

    @NotBlank(message = "Editora é obrigatório")
    @Column(name = "editora", nullable = false, length = 100)
    private String editora;

    @Column(name = "idioma", nullable = false, length = 100)
    private String idioma;

    @Column(name = "pagina", nullable = false)
    private int pagina;

    @Column(name = "faixa_etaria", nullable = false)
    private int faixaEtaria;

    @Column(name = "ano_publicacao", columnDefinition = "char(4)")
    private String anoDePublicacao;

    @Column(name = "disponivel", columnDefinition = "tinyint(1) default 1", nullable = false)
    private boolean disponivel;

    @OneToOne(mappedBy = "livro", cascade = CascadeType.ALL)
    public Emprestimo emprestimo;

}
