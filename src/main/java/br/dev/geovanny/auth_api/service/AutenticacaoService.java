package br.dev.geovanny.auth_api.service;

import br.dev.geovanny.auth_api.model.Usuario;
import br.dev.geovanny.auth_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);
        if (usuarioOptional.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }
        return usuarioOptional.get();
    }
}
