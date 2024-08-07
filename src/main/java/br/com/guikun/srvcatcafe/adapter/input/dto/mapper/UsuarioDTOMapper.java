package br.com.guikun.srvcatcafe.adapter.input.dto.mapper;

import br.com.guikun.srvcatcafe.adapter.input.dto.UsuarioDTO;
import br.com.guikun.srvcatcafe.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioDTOMapper {

    UsuarioDTOMapper INSTANCE = Mappers.getMapper(UsuarioDTOMapper.class);

    @Mapping(source = "usuario.nome", target = "nome")
    @Mapping(source = "usuario.email", target = "email")
    @Mapping(source = "usuario.telefone", target = "telefone")
    @Mapping(source = "usuario.dataCadastro", target = "dataCadastro")
    @Mapping(source = "usuario.tipoUsuario", target = "tipoUsuario")
    @Mapping(source = "usuario.enderecos", target = "enderecos")
    UsuarioDTO usuarioToDTO(Usuario usuario);

    List<UsuarioDTO> usuariosToDTOs(List<Usuario> usuarios);

    default Page<UsuarioDTO> toUsuarioDTOPage(Page<Usuario> usuarios) {
        List<UsuarioDTO> usuarioDTOs = usuariosToDTOs(usuarios.getContent());
        return new PageImpl<>(usuarioDTOs, usuarios.getPageable(), usuarios.getTotalElements());
    }
}
