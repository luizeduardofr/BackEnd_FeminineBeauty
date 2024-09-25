package feminine_beauty.api.repositories;

import feminine_beauty.api.domain.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select c.ativo
            from Cliente c
            where
            c.id = :id
            """)
    boolean findAtivoById(Long id);
}
