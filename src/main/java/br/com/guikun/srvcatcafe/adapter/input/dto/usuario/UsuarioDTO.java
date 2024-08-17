package br.com.guikun.srvcatcafe.adapter.input.dto.usuario;

import br.com.guikun.srvcatcafe.domain.enums.TipoUsuario;
import br.com.guikun.srvcatcafe.domain.model.Endereco;

import java.time.LocalDateTime;
import java.util.List;

public record UsuarioDTO(
        String nome,
        String email,
        String telefone,
        LocalDateTime dataCadastro,
        List<Endereco> enderecos,
        List<TipoUsuario> tipoUsuario) {}
