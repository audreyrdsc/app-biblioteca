package com.unifap.biblioteca.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Column(name = "data_emprestimo", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dataEmprestimo;

    @Column(name = "data_termino", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dataTermino;

    @Column(name = "data_devolucao", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

}
