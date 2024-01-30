package streaming.ifpb.api.filme;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoFilme(
		
		@NotNull
        Long id,
        String nome,
        String duracao,
        String genero,
        FaixaEtaria faixa_etaria,
        String sinopse, 
        Integer ano_lancamento
) {

}
