package feminine_beauty.api.repositories;

import feminine_beauty.api.domain.consulta.Consulta;
import feminine_beauty.api.dtos.consulta.StatusConsulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>, JpaSpecificationExecutor<Consulta> {

    boolean existsByFuncionarioIdAndDataAndMotivoCancelamentoIsNull(Long idFuncionario, LocalDateTime data);

    boolean existsByClienteIdAndDataBetween(Long idCliente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

}
