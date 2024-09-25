package feminine_beauty.api.repositories;

import feminine_beauty.api.domain.servico.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Page<Servico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select s.ativo
            from Servico s
            where
            s.id = :id
            """)
    boolean findAtivoById(Long id);
}
