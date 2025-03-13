package com.unifap.biblioteca.enums;

import lombok.Getter;

@Getter
public enum Authority {

    ADMIN(1,"Administrador"),
    USER(2,"Usuário");

    private final Integer code;
    private final String description;

    Authority(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

}
