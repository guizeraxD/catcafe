package br.com.guikun.srvcatcafe.adapter.input.impl;

import br.com.guikun.srvcatcafe.adapter.input.IUsuarioController;
import br.com.guikun.srvcatcafe.adapter.input.dto.UsuarioDTO;
import br.com.guikun.srvcatcafe.port.input.UsuarioUseCase;
import br.com.guikun.srvcatcafe.utils.LogEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/usuarios")
@Tag(name = "Informacoes a respeito dos Usuarios.", description = "Recursos para ver/manipular informações dos usuarios.")
public class UsuarioController implements IUsuarioController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);
    private static String LOG_LISTAR_USUARIOS = "listarUsuarios ";
    private final UsuarioUseCase usuarioUseCase;

    public UsuarioController(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> listarUsuarios(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        LOGGER.info(LogEnum.ENTRADA.getMsg(), LOG_LISTAR_USUARIOS, pageable);
        Page<UsuarioDTO> response = usuarioUseCase.listarUsuarios(pageable);
        LOGGER.info(LogEnum.SAIDA.getMsg(), LOG_LISTAR_USUARIOS, response);
        return ResponseEntity.ok(response);
    }


}
