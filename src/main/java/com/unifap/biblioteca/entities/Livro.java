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
    private int id;

    @Column(columnDefinition = "char(15)", nullable = false, unique = true, updatable = false)
    private String isbn;

    @Column(nullable = false, unique = true, length = 200)
    private String titulo;

    @Column(nullable = false, length = 100)
    private String autores;

    @Column(nullable = false, length = 100)
    private String editora;

    @Column(nullable = false, length = 100)
    private String idioma;

    @Column(nullable = false)
    private int pagina;

    @Column(nullable = false)
    private int faixaEtaria;

    @Column(columnDefinition = "date", nullable = false)
    private int anoDePublicacao;

    @Column(nullable = false)
    private boolean disponivel;

    public void setAnoDePublicacao(int ano) {
        this.anoDePublicacao = Year.of(ano).getValue();
    }

    public Year getAnoDePublicacao() {
        return Year.of(anoDePublicacao);
    }
}
