package streaming.ifpb.api.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import streaming.ifpb.api.filme.DadosAtualizacaoFilme;
import streaming.ifpb.api.filme.DadosCadastroFilme;
import streaming.ifpb.api.filme.DadosListagemFilme;
import streaming.ifpb.api.filme.Filme;
import streaming.ifpb.api.filme.FilmeRepository;

@RestController
@RequestMapping("filmes")

public class FilmeController {
	
	@Autowired
	private FilmeRepository repository;
	
	@PostMapping
	@Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroFilme dados) {
		 System.out.println(dados);
		 repository.save(new Filme(dados));
	}
	
	@GetMapping
	public Page<DadosListagemFilme> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemFilme::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoFilme dados) {
	    Filme filme = repository.getReferenceById(dados.id());
	    
	    if (filme != null) {
	        filme.atualizarInformacoes(dados);
	        repository.save(filme);
	    } else {
	        System.out.println("Filme não encontrado para o ID: " + dados.id());
	    }
	}
	
	@DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var filme = repository.getReferenceById(id);
        filme.excluir();
    }

	@PostMapping("/buscarPorNome")
	public Page<DadosListagemFilme> buscarPorNome(@RequestBody Map<String, String> requestBody,
	                                              @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
	    String nome = requestBody.get("nome");

	    Page<Filme> filmes = repository.findByNome(nome, paginacao);

	    List<DadosListagemFilme> listaFilmesAtivos = filmes.getContent().stream()
	            .filter(Filme::isAtivo)
	            .map(DadosListagemFilme::new)
	            .collect(Collectors.toList());

	    if (listaFilmesAtivos.isEmpty()) {
	        System.out.println("Nenhum filme ativo encontrado para o nome: " + nome);
	    }

	    return new PageImpl<>(listaFilmesAtivos, paginacao, filmes.getTotalElements());
	}

}
