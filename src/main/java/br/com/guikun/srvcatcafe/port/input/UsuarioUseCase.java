package br.com.guikun.srvcatcafe.port.input;

import br.com.guikun.srvcatcafe.adapter.input.dto.usuario.CadastrarUsuario;
import br.com.guikun.srvcatcafe.adapter.input.dto.usuario.UsuarioDTO;
import br.com.guikun.srvcatcafe.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface UsuarioUseCase {
        Page<UsuarioDTO> listarUsuarios(Pageable pageable);

        Usuario consultarUsuarioAuth(String email);
        UsuarioDTO consultarUsuario(String email);
        void cadastrarUsuario(CadastrarUsuario usuario);
        void removerUsuario(String email);
}
