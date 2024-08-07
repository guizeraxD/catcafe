package br.com.guikun.srvcatcafe.adapter.input;

import br.com.guikun.srvcatcafe.adapter.input.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
}
