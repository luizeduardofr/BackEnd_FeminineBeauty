package feminine_beauty.api.repositories;

import feminine_beauty.api.domain.consulta.Consulta;
import feminine_beauty.api.dtos.consulta.StatusConsulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>, JpaSpecificationExecutor<Consulta> {

    boolean existsByFuncionarioIdAndDataAndMotivoCancelamentoIsNull(Long idFuncionario, LocalDateTime data);

    boolean existsByClienteIdAndDataBetweenAndStatus(Long idCliente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario, StatusConsulta status);
    @Query(value = """
            SELECT COUNT(*)
              FROM consultas c
              JOIN servicos s ON c.servico_id = s.id
             WHERE c.funcionario_id = :idFuncionario
               AND c.motivo_cancelamento IS NULL
               AND (:data BETWEEN c.data AND DATE_ADD(c.data, INTERVAL s.duracao MINUTE)
                OR (c.data BETWEEN :data AND DATE_ADD(:data, INTERVAL :duracao MINUTE)))
            """, nativeQuery = true)
    Long countByFuncionarioIdAndDataWithDuracao(Long idFuncionario, LocalDateTime data, Long duracao);
}
