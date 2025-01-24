package br.dev.geovanny.auth_api.controller;

import br.dev.geovanny.auth_api.dto.UsuarioDTO;
import br.dev.geovanny.auth_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    private UsuarioDTO salvar(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.salvar(usuarioDTO);
    }

    @GetMapping("/admin")
    private String getAdmin() {
        return "Permissão de administrador";
    }

    @GetMapping("/user")
    private String getUser() {
        return "Permissão de usuario";
    }
}
