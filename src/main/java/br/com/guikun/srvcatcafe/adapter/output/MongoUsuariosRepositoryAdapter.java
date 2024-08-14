package br.com.guikun.srvcatcafe.adapter.output;

import br.com.guikun.srvcatcafe.domain.exception.BusinessException;
import br.com.guikun.srvcatcafe.domain.exception.ChaveDuplicadaException;
import br.com.guikun.srvcatcafe.domain.exception.NaoEncontradoException;
import br.com.guikun.srvcatcafe.domain.model.Usuario;
import br.com.guikun.srvcatcafe.port.output.MongoUsuariosPort;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    @Override
    public Usuario consultarUsuario(String email) {
        Optional<Usuario> usuario = usuariosRepository.findByEmail(email);
        if(usuario.isPresent())
            return usuario.get();
        else throw new NaoEncontradoException(HttpStatus.NOT_FOUND.value(), "Usuario não encontrado com email " + email);
    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        try{
            usuariosRepository.insert(usuario);
        }catch (DuplicateKeyException e){
            throw new ChaveDuplicadaException(e.getLocalizedMessage());
        }catch(Exception e){
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao acessar o banco de dados");
        }
    }

    @Override
    public void removerUsuario(String email) {
        if(usuariosRepository.existsByEmail(email))
            usuariosRepository.deleteByEmail(email);
        else throw new NaoEncontradoException(HttpStatus.NOT_FOUND.value(), "Usuario não encontrado");
    }
}
