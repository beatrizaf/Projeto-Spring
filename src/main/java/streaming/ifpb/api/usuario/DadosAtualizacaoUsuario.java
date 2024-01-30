package streaming.ifpb.api.usuario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoUsuario(
		@NotNull
        Long id,
        String nome,
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "O e-mail fornecido não é válido.")
        String email,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "A senha fornecida não atende aos requisitos mínimos de segurança.")
        String senha,
        Assinatura assinatura) {

}
