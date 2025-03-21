package com.unifap.biblioteca.entities;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;

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

    @Column(name = "ano_publicacao", columnDefinition = "date", nullable = false)
    private int anoDePublicacao;

    @Column(name = "disponivel", nullable = false)
    private boolean disponivel;

    public void setAnoDePublicacao(int ano) {
        this.anoDePublicacao = Year.of(ano).getValue();
    }

    public Year getAnoDePublicacao() {
        return Year.of(anoDePublicacao);
    }

    @OneToOne(mappedBy = "livro", cascade = CascadeType.ALL)
    public Emprestimo emprestimo;

}
