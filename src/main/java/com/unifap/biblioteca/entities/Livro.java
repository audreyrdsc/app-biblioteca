package com.unifap.biblioteca.entities;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.List;

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

    @Column(name = "isbn", columnDefinition = "char(15)", nullable = false, unique = true, updatable = false)
    private String isbn;

    @Column(name = "titulo", nullable = false, unique = true, length = 200)
    private String titulo;

    @Column(name = "autores", nullable = false, length = 100)
    private String autores;

    @Column(name = "editora", nullable = false, length = 100)
    private String editora;

    @Column(name = "idioma", nullable = false, length = 100)
    private String idioma;

    @Column(name = "pagina", nullable = false)
    private int pagina;

    @Column(name = "faixa_etaria", nullable = false)
    private int faixaEtaria;

    @Column(name = "disponivel", columnDefinition = "tinyint(1) default 1", nullable = false)
    private boolean disponivel;

    @Column(name = "ano_publicacao", columnDefinition = "char(4)")
    private String anoDePublicacao;

    @OneToOne(mappedBy = "livro", cascade = CascadeType.ALL)
    public Emprestimo emprestimo;

}
