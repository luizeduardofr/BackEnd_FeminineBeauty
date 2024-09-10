package feminine_beauty.api.repositories;

import feminine_beauty.api.domain.cliente.Cliente;
import feminine_beauty.api.domain.servico.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Page<Servico> findAllByAtivoTrue(Pageable paginacao);
}
