package br.com.guikun.srvcatcafe.adapter.input.dto;

import br.com.guikun.srvcatcafe.adapter.input.dto.validation.ValidaCadastrarTipoUsuario;
import br.com.guikun.srvcatcafe.domain.enums.TipoUsuario;
import br.com.guikun.srvcatcafe.domain.model.Endereco;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record CadastrarUsuario(@NotNull String nome,
                               @NotNull String email,
                               @NotNull String telefone,
                               @NotNull LocalDateTime dataCadastro,
                               @NotNull @Size(min = 1) List<Endereco> enderecos,
                               @NotNull @ValidaCadastrarTipoUsuario TipoUsuario tipoUsuario) {}
