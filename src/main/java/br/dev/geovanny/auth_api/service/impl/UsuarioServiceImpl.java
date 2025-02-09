package br.dev.geovanny.auth_api.service.impl;

import br.dev.geovanny.auth_api.dto.UsuarioDTO;
import br.dev.geovanny.auth_api.infra.exceptions.BusinessException;
import br.dev.geovanny.auth_api.model.Usuario;
import br.dev.geovanny.auth_api.repository.UsuarioRepository;
import br.dev.geovanny.auth_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {

        Usuario usuarioJaExiste = usuarioRepository.findByLogin(usuarioDTO.login());

        if (usuarioJaExiste != null) {
            throw new BusinessException("Usuário já existe!!");
        }

        var passwordHash = passwordEncoder.encode(usuarioDTO.senha());
        Usuario usuario = new Usuario(usuarioDTO.nome(), usuarioDTO.login(), passwordHash, usuarioDTO.role());
        Usuario novoUsuario = usuarioRepository.save(usuario);

        return new UsuarioDTO(novoUsuario.getNome(), novoUsuario.getLogin(), novoUsuario.getSenha(), novoUsuario.getRole());
    }
}
