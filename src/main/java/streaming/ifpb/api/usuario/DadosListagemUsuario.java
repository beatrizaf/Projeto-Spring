package streaming.ifpb.api.usuario;

public record DadosListagemUsuario(Long id, String nome, String email, String senha, Assinatura assinatura) {

	 public DadosListagemUsuario(Usuario usuario) {
	        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), 
	        	 usuario.getAssinatura());
	 }
}
