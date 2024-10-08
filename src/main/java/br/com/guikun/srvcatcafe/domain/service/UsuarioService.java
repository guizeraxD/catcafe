package br.com.guikun.srvcatcafe.domain.service;

import br.com.guikun.srvcatcafe.adapter.input.dto.usuario.CadastrarUsuario;
import br.com.guikun.srvcatcafe.adapter.input.dto.usuario.UsuarioDTO;
import br.com.guikun.srvcatcafe.adapter.input.dto.usuario.mapper.CadastrarUsuarioMapper;
import br.com.guikun.srvcatcafe.adapter.input.dto.usuario.mapper.UsuarioDTOMapper;
import br.com.guikun.srvcatcafe.domain.exception.NaoEncontradoException;
import br.com.guikun.srvcatcafe.domain.model.Usuario;
import br.com.guikun.srvcatcafe.port.input.UsuarioUseCase;
import br.com.guikun.srvcatcafe.port.output.MongoUsuariosPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UsuarioUseCase, UserDetailsService {

    private final MongoUsuariosPort usuarioRepository;

    public UsuarioService(MongoUsuariosPort usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public Page<UsuarioDTO> listarUsuarios(Pageable pageable) {
        Page<Usuario> result = usuarioRepository.listarUsuarios(pageable);
        if(result.isEmpty())
            throw new NaoEncontradoException(HttpStatus.NOT_FOUND.value(), "Nenhum usuario encontrado.");
        return result.map(
                UsuarioDTOMapper.INSTANCE::usuarioToDTO
        );
    }
    @Override
    public Usuario consultarUsuarioAuth(String email) {
        return usuarioRepository.consultarUsuario(email);
    }

    @Override
    public UsuarioDTO consultarUsuario(String email) {
        return UsuarioDTOMapper.INSTANCE.usuarioToDTO(
                usuarioRepository.consultarUsuario(email)
        );
    }

    @Override
    public void cadastrarUsuario(CadastrarUsuario usuario) {
        usuarioRepository.cadastrarUsuario(CadastrarUsuarioMapper.INSTANCE.cadastrarUsuarioToUsuario(usuario));
    }

    @Override
    public void removerUsuario(String email) {
        usuarioRepository.removerUsuario(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.consultarUsuario(username);
    }
}
