package br.com.guikun.srvcatcafe.domain.service;

import br.com.guikun.srvcatcafe.adapter.input.dto.UsuarioDTO;
import br.com.guikun.srvcatcafe.adapter.input.dto.mapper.UsuarioDTOMapper;
import br.com.guikun.srvcatcafe.domain.exception.NaoEncontradoException;
import br.com.guikun.srvcatcafe.domain.model.Usuario;
import br.com.guikun.srvcatcafe.port.input.UsuarioUseCase;
import br.com.guikun.srvcatcafe.port.output.MongoUsuariosPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UsuarioUseCase {

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
}
