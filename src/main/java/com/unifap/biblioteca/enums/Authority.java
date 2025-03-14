package com.unifap.biblioteca.enums;

import lombok.Getter;

@Getter
public enum Authority {

    ADMIN("ADMIN","Administrador"),
    USER("INVIT","Convidado");

    private final String name;
    private final String description;

    Authority(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
