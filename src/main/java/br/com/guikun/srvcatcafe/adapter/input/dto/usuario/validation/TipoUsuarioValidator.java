package br.com.guikun.srvcatcafe.adapter.input.dto.usuario.validation;

import br.com.guikun.srvcatcafe.domain.enums.TipoUsuario;
import br.com.guikun.srvcatcafe.domain.exception.TipoUsuarioInvalidoException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoUsuarioValidator implements ConstraintValidator<ValidaCadastrarTipoUsuario, TipoUsuario> {
    @Override
    public boolean isValid(TipoUsuario tipoUsuario, ConstraintValidatorContext constraintValidatorContext) {
        if (tipoUsuario.equals(TipoUsuario.ADMIN)){
            throw new TipoUsuarioInvalidoException("Não é possivel cadastrar um usuario do tipo ADMIN");
        }
        return true;
    }
}
