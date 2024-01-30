package streaming.ifpb.api.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuario(
		@NotBlank
		String nome,
		
		@NotBlank
		@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "O e-mail fornecido não é válido.")
		String email,

		@NotBlank
		@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "A senha fornecida não atende aos requisitos mínimos de segurança.")
		String senha, 
		
		@NotNull
		Assinatura assinatura) {
}
