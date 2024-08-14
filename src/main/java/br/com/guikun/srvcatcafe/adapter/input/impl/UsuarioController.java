package br.com.guikun.srvcatcafe.adapter.input.impl;

import br.com.guikun.srvcatcafe.adapter.input.IUsuarioController;
import br.com.guikun.srvcatcafe.adapter.input.dto.CadastrarUsuario;
import br.com.guikun.srvcatcafe.adapter.input.dto.UsuarioDTO;
import br.com.guikun.srvcatcafe.domain.model.Usuario;
import br.com.guikun.srvcatcafe.port.input.UsuarioUseCase;
import br.com.guikun.srvcatcafe.utils.LogEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/usuarios")
@Tag(name = "Informacoes a respeito dos Usuarios.", description = "Recursos para ver/manipular informações dos usuarios.")
public class UsuarioController implements IUsuarioController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);
    private static String LOG_LISTAR_USUARIOS = "listarUsuarios ";
    private static String LOG_CONSULTAR_USUARIO = "consultarUsuario ";
    private static String LOG_CADASTRAR_USUARIO = "cadastrarUsuario ";
    private static String LOG_REMOVER_USUARIO = "cadastrarUsuario ";
    private final UsuarioUseCase usuarioUseCase;

    public UsuarioController(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @GetMapping
    @Override
    public ResponseEntity<Page<UsuarioDTO>> listarUsuarios(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        LOGGER.info(LogEnum.ENTRADA.getMsg(), LOG_LISTAR_USUARIOS, pageable);
        Page<UsuarioDTO> response = usuarioUseCase.listarUsuarios(pageable);
        LOGGER.info(LogEnum.SAIDA.getMsg(), LOG_LISTAR_USUARIOS, response);
        return ResponseEntity.ok(response);
    }
    @Override
    @GetMapping("email/{email}")
    public ResponseEntity<UsuarioDTO> consultarUsuario(@PathVariable String email) {
        LOGGER.info(LogEnum.ENTRADA.getMsg(), LOG_CONSULTAR_USUARIO, email);
        UsuarioDTO usuario = usuarioUseCase.consultarUsuario(email);
        LOGGER.info(LogEnum.SAIDA.getMsg(), LOG_CONSULTAR_USUARIO, usuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    @Override
    public ResponseEntity<CadastrarUsuario> cadastrarUsuario(@RequestBody @Valid CadastrarUsuario usuario) {
        LOGGER.info(LogEnum.ENTRADA.getMsg(), LOG_CADASTRAR_USUARIO, usuario);
        usuarioUseCase.cadastrarUsuario(usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/remover/{email}")
    @Override
    public ResponseEntity<UsuarioDTO> removerUsuario(@PathVariable String email) {
        LOGGER.info(LogEnum.ENTRADA.getMsg(), LOG_REMOVER_USUARIO, email);
        usuarioUseCase.removerUsuario(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
