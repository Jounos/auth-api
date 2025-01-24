package br.dev.geovanny.auth_api.service;


import br.dev.geovanny.auth_api.dto.AuthDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AutenticacaoService extends UserDetailsService {

    public String obterToken(AuthDTO authDTO);

    public String validaTokenJWT(String token);
}
