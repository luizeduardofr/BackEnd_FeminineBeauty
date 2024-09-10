package feminine_beauty.api.repositories;

import feminine_beauty.api.domain.funcionario.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Page<Funcionario>findAllByAtivoTrue(Pageable paginacao);
}
