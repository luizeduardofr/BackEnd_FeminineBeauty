package feminine_beauty.api.repositories;

import feminine_beauty.api.domain.funcionario.Funcionario;
import feminine_beauty.api.domain.servico.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Page<Funcionario>findAllByAtivoTrue(Pageable paginacao);

    List<Funcionario> findAllByAtivoTrueAndServicosId(Long idServico);

    Funcionario findByUsuarioId(Long id);

//    @Query("""
//            select f from Funcionario f
//            where
//            f.ativo = true
//            and
//            f.servico = :servico
//            and
//            f.id not in(
//                select f.funcionario.id from Consulta c
//                where
//                c.data = :data
//            and
//                c.motivoCancelamento is null
//            )
//            order by rand()
//            limit 1
//            """)
//    Funcionario escolherFuncionarioAleatorioLivreNaData(Servico servico, LocalDateTime data);

    @Query("""
            select f.ativo
            from Funcionario f
            where
            f.id = :id
            """)
    Boolean findAtivoById(Long id);
}
