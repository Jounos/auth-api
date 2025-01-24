package br.dev.geovanny.auth_api.controller;

import br.dev.geovanny.auth_api.dto.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    private String auth(@RequestBody AuthDTO authDTO) {

        var usuarioAutenticado = new UsernamePasswordAuthenticationToken(authDTO.login(), authDTO.senha());
        authenticationManager.authenticate(usuarioAutenticado);
        return "token....";
    }
}
