package br.dev.geovanny.auth_api.dto;

import br.dev.geovanny.auth_api.enums.RoleEnum;

public record UsuarioDTO(
        String nome,
        String login,
        String senha,
        RoleEnum role
) {

}
