package streaming.ifpb.api.filme;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroFilme(
		
		@NotBlank
		String nome,
		
		@NotBlank
		@Pattern(regexp = "([01]?[0-9]|2[0-3])h ([0-5]?[0-9])min")
		String duracao, 
		
		@NotBlank
		String genero, 
		
		@NotNull
		FaixaEtaria faixa_etaria, 
		
		@NotBlank
		String sinopse, 
		
		@NotNull
		@Digits(integer = 4, fraction = 0)
		Integer ano_lancamento) 
{

}
