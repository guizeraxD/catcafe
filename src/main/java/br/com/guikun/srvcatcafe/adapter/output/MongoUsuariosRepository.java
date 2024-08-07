package br.com.guikun.srvcatcafe.adapter.output;

import br.com.guikun.srvcatcafe.domain.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoUsuariosRepository extends MongoRepository<Usuario, String> {
}
