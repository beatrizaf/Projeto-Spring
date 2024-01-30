package streaming.ifpb.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import streaming.ifpb.api.usuario.DadosAtualizacaoUsuario;
import streaming.ifpb.api.usuario.DadosCadastroUsuario;
import streaming.ifpb.api.usuario.TipoUsuario;
import streaming.ifpb.api.usuario.Usuario;
import streaming.ifpb.api.usuario.UsuarioLoginRequest;
import streaming.ifpb.api.usuario.UsuarioRepository;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping("/cadastro")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
	    System.out.println("Aqui");
	    try {
	        System.out.println("Recebendo dados de cadastro: " + dados);
	        repository.save(new Usuario(dados, TipoUsuario.CLIENTE));
	        return ResponseEntity.ok("Cadastro realizado com sucesso!");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Erro durante o cadastro: " + e.getMessage());
	    }
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados) {
	    Usuario usuario = repository.getReferenceById(dados.id());
	    
	    if (usuario != null) {
	        usuario.atualizarInformacoes(dados);
	        repository.save(usuario);
	    } 
	}
	
	@DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        usuario.excluir();
    }
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UsuarioLoginRequest request) {
	    String email = request.getEmail();
	    String senha = request.getSenha();
	    
	    System.out.println("Aqui");
	    System.out.println(email);
	    System.out.println(senha);

	    Usuario usuario = repository.findByEmail(email);
	    
	    System.out.println(usuario);

	    if (usuario != null && usuario.isAtivo() && usuario.verificarSenha(senha)) {
	        if (usuario.getTipo() == TipoUsuario.ADMIN) {
	            return ResponseEntity.ok("Login bem-sucedido como administrador");
	        } else if (usuario.getTipo() == TipoUsuario.CLIENTE) {
	            return ResponseEntity.ok("Login bem-sucedido como cliente");
	        }
	    }

	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas. Tente novamente.");
	}
}
