package streaming.ifpb.api.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario {
	
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
    
     private String nome;
     private String email;
     private String senha;
     
     @Enumerated(EnumType.STRING)
     private Assinatura assinatura;
     
     private boolean ativo;
     
     @Enumerated(EnumType.STRING)
     private TipoUsuario tipo;
     
     public Usuario() { 
     }
     
     public Usuario(DadosCadastroUsuario dados, TipoUsuario tipo) {
    	 this.tipo = tipo;
    	 this.ativo = true;
         this.setNome(dados.nome());
         this.setEmail(dados.email());
         this.setSenha(dados.senha());
         this.assinatura = dados.assinatura();
     }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	
	public Assinatura getAssinatura() {
		return assinatura;
	}
	
	public boolean isAtivo() {
        return ativo;
    }
	
	public TipoUsuario getTipo() {
		return tipo;
	}

	public void atualizarInformacoes(DadosAtualizacaoUsuario dados) {
		if (dados.nome() != null) {
            this.nome = dados.nome();
        }
		
		if (dados.email() != null) {
            this.email = dados.email();
        }
		
		if (dados.senha() != null) {
            this.senha = dados.senha();
        }
		
		if (dados.assinatura() != null) {
            this.assinatura = dados.assinatura();
        }
				
	}

	public void excluir() {
		this.ativo = false;
	}

	public boolean verificarSenha(String senhaDigitada) {
	    return senha != null && senha.equals(senhaDigitada);
	}
	
	public static Usuario criarAdmin(DadosCadastroUsuario dados) {
        return new Usuario(dados, TipoUsuario.ADMIN);
    }
}
