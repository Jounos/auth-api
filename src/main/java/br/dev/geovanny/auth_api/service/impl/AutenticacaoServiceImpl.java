package br.dev.geovanny.auth_api.service.impl;

import br.dev.geovanny.auth_api.dto.AuthDTO;
import br.dev.geovanny.auth_api.infra.exceptions.DevException;
import br.dev.geovanny.auth_api.model.Usuario;
import br.dev.geovanny.auth_api.repository.UsuarioRepository;
import br.dev.geovanny.auth_api.service.AutenticacaoService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(login);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado. ");
        }
        return usuario;
    }

    @Override
    public String obterToken(AuthDTO authDTO) {
        Usuario usuario = this.usuarioRepository.findByLogin(authDTO.login());
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado. ");
        }
        return this.gerarTokenJWT(usuario);
    }

    public String gerarTokenJWT(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new DevException("Erro ao tentar gerar o token!" + e.getMessage());
        }
    }

    public String validaTokenJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

}
