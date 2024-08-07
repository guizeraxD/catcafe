package br.com.guikun.srvcatcafe.port.output;

import br.com.guikun.srvcatcafe.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MongoUsuariosPort {
    Page<Usuario> listarUsuarios(Pageable pageable);
}
