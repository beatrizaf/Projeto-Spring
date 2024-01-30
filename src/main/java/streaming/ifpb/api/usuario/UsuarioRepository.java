package streaming.ifpb.api.usuario;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Page<Usuario> findAllByAtivoTrue(Pageable paginacao);

	Page<Usuario> findByNome(String nome, Pageable paginacao);

	Usuario findByEmail(String email);
}
