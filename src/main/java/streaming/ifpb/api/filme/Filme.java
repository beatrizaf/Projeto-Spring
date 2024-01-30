package streaming.ifpb.api.filme;

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

@Table(name = "filmes")
@Entity(name = "Filme")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Filme {
	
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
    
     private String nome;
     private String duracao;
     private String genero;
     
     @Enumerated(EnumType.STRING)
     private FaixaEtaria faixa_etaria;
     
     private String sinopse;
     private Integer ano_lancamento;
     
     private boolean ativo;
     
     public Filme() { 
     }
     
     public Filme(DadosCadastroFilme dados) {
    	 this.ativo = true;
         this.setNome(dados.nome());
         this.setDuracao(dados.duracao());
         this.setGenero(dados.genero());
         this.faixa_etaria = dados.faixa_etaria();
         this.setSinopse(dados.sinopse());
         this.setAno_lancamento(dados.ano_lancamento());
     }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public Integer getAno_lancamento() {
		return ano_lancamento;
	}

	public void setAno_lancamento(Integer ano_lancamento) {
		this.ano_lancamento = ano_lancamento;
	}
	
	public FaixaEtaria getFaixa_etaria() {
		return faixa_etaria;
	}

	public void setFaixa_etaria(FaixaEtaria faixa_etaria) {
		this.faixa_etaria = faixa_etaria;
	}
	
	public Long getId() {
		return id;
	}
	
	public boolean isAtivo() {
        return ativo;
    }

	public void atualizarInformacoes(DadosAtualizacaoFilme dados) {
		if (dados.nome() != null) {
            this.nome = dados.nome();
        }
		
		if (dados.duracao() != null) {
            this.duracao = dados.duracao();
        }
		
		if (dados.genero() != null) {
            this.genero = dados.genero();
        }
		
		if (dados.faixa_etaria() != null) {
            this.faixa_etaria = dados.faixa_etaria();
        }
		
		if (dados.sinopse() != null) {
            this.sinopse = dados.sinopse();
        }
		
		if (dados.ano_lancamento() != null) {
            this.ano_lancamento = dados.ano_lancamento();
        }		
	}

	public void excluir() {
		this.ativo = false;
	}

}
