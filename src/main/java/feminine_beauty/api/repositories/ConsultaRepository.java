package feminine_beauty.api.repositories;

import feminine_beauty.api.domain.consulta.Consulta;
import feminine_beauty.api.dtos.consulta.StatusConsulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Page<Consulta> findByClienteIdAndStatus(Pageable paginacao, Long idCliente, StatusConsulta status);

    Page<Consulta> findByClienteIdAndStatusNot(Pageable paginacao, Long idCliente, StatusConsulta status);

    boolean existsByFuncionarioIdAndDataAndMotivoCancelamentoIsNull(Long idFuncionario, LocalDateTime data);

    boolean existsByClienteIdAndDataBetween(Long idCliente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    Page<Consulta> findByFuncionarioIdAndStatus(Pageable paginacao, Long idFuncionario, StatusConsulta statusConsulta);

    Page<Consulta> findByFuncionarioIdAndStatusNot(Pageable paginacao, Long idCliente, StatusConsulta status);
}
