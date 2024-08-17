package br.com.guikun.srvcatcafe.adapter.input;

import br.com.guikun.srvcatcafe.adapter.input.dto.auth.AuthRequest;
import br.com.guikun.srvcatcafe.adapter.input.dto.auth.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthController {

    @Operation(summary = "Endpoint de login",
            description = "Retorna o Authorization token necessário para acessar as apis",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "",
                            content = @Content(mediaType = "application/json")
                    ),@ApiResponse(
                    responseCode = "500",
                    description = "Erro de servidor.",
                    content = @Content(mediaType = "application/json")
            ),
            }
    )
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest loginRequest);
}
