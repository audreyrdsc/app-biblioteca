package com.unifap.biblioteca.entities;

import com.unifap.biblioteca.enums.Authority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @Size(min = 2, max = 100)
    @NotBlank
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    @Size(min = 2, max = 60)
    @NotBlank
    private String password;

    @Column(columnDefinition = "tinyint(1) default 1", nullable = false)
    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "char(5)", nullable = false)
    private Authority authority;
}
