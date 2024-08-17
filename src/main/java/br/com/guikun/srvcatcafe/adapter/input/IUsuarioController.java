package br.com.guikun.srvcatcafe.adapter.input;

import br.com.guikun.srvcatcafe.adapter.input.dto.usuario.CadastrarUsuario;
import br.com.guikun.srvcatcafe.adapter.input.dto.usuario.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

public interface IUsuarioController {
    @Operation(summary = "Listar usuarios",
                description = "Listar usuarios cadastrados na base",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Usuarios retornados com sucesso!",
                        content = @Content(mediaType = "application/json")
                ),@ApiResponse(
                            responseCode = "500",
                            description = "Erro de servidor.",
                            content = @Content(mediaType = "application/json")
                ),
            }
    )
    ResponseEntity<Page<UsuarioDTO>> listarUsuarios(@PageableDefault(page = 0, size = 10) Pageable pageable);
    @Operation(summary = "Consulta um usuario",
            description = "Consulta um usuario cadastrado na base",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuarios retornados com sucesso!",
                            content = @Content(mediaType = "application/json")
                    ),@ApiResponse(
                    responseCode = "500",
                    description = "Erro de servidor.",
                    content = @Content(mediaType = "application/json")
            ),
            }
    )
    ResponseEntity<UsuarioDTO> consultarUsuario(String email);

    @Operation(summary = "Cadastra um usuario",
            description = "Cadastra um usuario na base",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Usuarios retornados com sucesso!",
                            content = @Content(mediaType = "application/json")
                    ),@ApiResponse(
                    responseCode = "500",
                    description = "Erro de servidor.",
                    content = @Content(mediaType = "application/json")
            ),
            }
    )
    ResponseEntity<CadastrarUsuario> cadastrarUsuario(CadastrarUsuario usuario);

    @Operation(summary = "Remove um usuario",
            description = "Remove um usuario na base",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuarios retornados com sucesso!",
                            content = @Content(mediaType = "application/json")
                    ),@ApiResponse(
                    responseCode = "500",
                    description = "Erro de servidor.",
                    content = @Content(mediaType = "application/json")
            ),
            }
    )
    ResponseEntity<UsuarioDTO> removerUsuario(String email);
}
