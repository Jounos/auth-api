package br.dev.geovanny.auth_api.service;

import br.dev.geovanny.auth_api.dto.UsuarioDTO;
import br.dev.geovanny.auth_api.model.Usuario;
import br.dev.geovanny.auth_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {

        Optional<Usuario> usuarioJaExiste = usuarioRepository.findByLogin(usuarioDTO.login());

        if (usuarioJaExiste.isPresent()) {
            throw new RuntimeException("Usuário já existe!!");
        }

        var passwordHash = passwordEncoder.encode(usuarioDTO.senha());
        Usuario usuario = new Usuario(usuarioDTO.nome(), usuarioDTO.login(), passwordHash);
        Usuario novoUsuario = usuarioRepository.save(usuario);

        return new UsuarioDTO(novoUsuario.getNome(), novoUsuario.getLogin(), novoUsuario.getSenha());
    }
}
