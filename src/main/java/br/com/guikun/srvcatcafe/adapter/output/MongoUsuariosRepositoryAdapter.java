package br.com.guikun.srvcatcafe.adapter.output;

import br.com.guikun.srvcatcafe.domain.model.Usuario;
import br.com.guikun.srvcatcafe.port.output.MongoUsuariosPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class MongoUsuariosRepositoryAdapter implements MongoUsuariosPort {

    private final MongoUsuariosRepository usuariosRepository;

    public MongoUsuariosRepositoryAdapter(MongoUsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuariosRepository.findAll(pageable);
    }
}
