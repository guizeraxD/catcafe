package br.com.guikun.srvcatcafe.port.input;

import br.com.guikun.srvcatcafe.adapter.input.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface UsuarioUseCase {
        Page<UsuarioDTO> listarUsuarios(Pageable pageable);
}
