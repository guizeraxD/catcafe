package br.com.guikun.srvcatcafe.adapter.input.dto.usuario.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoUsuarioValidator.class)
public @interface ValidaCadastrarTipoUsuario {
    String message() default "Não é permitido cadastrar um usuário como ADMIN.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
