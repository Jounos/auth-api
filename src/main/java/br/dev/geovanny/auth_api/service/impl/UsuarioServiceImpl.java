package br.dev.geovanny.auth_api.service.impl;

import br.dev.geovanny.auth_api.dto.UsuarioDTO;
import br.dev.geovanny.auth_api.model.Usuario;
import br.dev.geovanny.auth_api.repository.UsuarioRepository;
import br.dev.geovanny.auth_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {

        Optional<Usuario> usuarioJaExiste = usuarioRepository.findByLogin(usuarioDTO.login());

        if (usuarioJaExiste.isPresent()) {
            throw new RuntimeException("Usuário já existe!!");
        }

        Usuario usuario = new Usuario(usuarioDTO.nome(), usuarioDTO.login(), usuarioDTO.senha());
        Usuario novoUsuario = usuarioRepository.save(usuario);

        return new UsuarioDTO(novoUsuario.getNome(), novoUsuario.getLogin(), novoUsuario.getSenha());
    }
}
