package com.unifap.biblioteca.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "emprestimo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_emprestimo", columnDefinition = "date", nullable = false)
    private LocalDate dataEmprestimo;

    @Column(name = "data_termino", columnDefinition = "date", nullable = false)
    private LocalDate dataTermino;

    @Column(name = "data_devolucao", columnDefinition = "date", nullable = true)
    private LocalDate dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

}
