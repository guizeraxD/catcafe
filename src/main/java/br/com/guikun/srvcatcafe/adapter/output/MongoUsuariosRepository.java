package br.com.guikun.srvcatcafe.adapter.output;

import br.com.guikun.srvcatcafe.domain.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoUsuariosRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
    void deleteByEmail(String email);
    boolean existsByEmail(String email);
}
