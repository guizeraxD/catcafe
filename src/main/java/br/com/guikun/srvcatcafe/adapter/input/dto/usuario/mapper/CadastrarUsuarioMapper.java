package br.com.guikun.srvcatcafe.adapter.input.dto.usuario.mapper;

import br.com.guikun.srvcatcafe.adapter.input.dto.usuario.CadastrarUsuario;
import br.com.guikun.srvcatcafe.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CadastrarUsuarioMapper {

    CadastrarUsuarioMapper INSTANCE = Mappers.getMapper(CadastrarUsuarioMapper.class);

    Usuario cadastrarUsuarioToUsuario(CadastrarUsuario cadastrarUsuario);
}
