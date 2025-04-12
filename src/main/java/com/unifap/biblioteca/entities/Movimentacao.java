package com.unifap.biblioteca.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

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

    // data e hora da criação da movimentação na tabela emprestimo
    @CreationTimestamp
    @Column(columnDefinition = "datetime", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // user que criou a movimentação
    @CreatedBy
    @Column(length = 50, nullable = false, updatable = false)
    private String createdBy;

    // data e hora de última modificação na tabela emprestimo
    @UpdateTimestamp
    @Column(columnDefinition = "datetime", nullable = false)
    private LocalDateTime updatedAt;

    // user da última modificação
    @LastModifiedBy
    @Column(length = 50, nullable = false)
    private String updatedBy;

}
