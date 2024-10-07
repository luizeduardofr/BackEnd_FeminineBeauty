package feminine_beauty.api.repositories;

import feminine_beauty.api.domain.servico.Servico;
import feminine_beauty.api.dtos.servico.DadosListagemServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Page<Servico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select s.ativo
            from Servico s
            where
            s.id = :id
            """)
    boolean findAtivoById(Long id);

    List<Servico> findAllByAtivoTrueAndFuncionariosId(Long idFuncionario);
}
