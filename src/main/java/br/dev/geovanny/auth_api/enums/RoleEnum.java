package br.dev.geovanny.auth_api.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("admin"),
    USER("user");

    private String role;

    private RoleEnum(String role) {}
}
