package streaming.ifpb.api.filme;


public record DadosListagemFilme (Long id, String nome, String duracao, String genero, 
		                          FaixaEtaria faixa_etaria, String sinopse, Integer ano_lancamento) {

	
	  public DadosListagemFilme(Filme filme) {
	        this(filme.getId(), filme.getNome(), filme.getDuracao(), filme.getGenero(), 
	        	 filme.getFaixa_etaria(), filme.getSinopse(), filme.getAno_lancamento());
	  }
}
