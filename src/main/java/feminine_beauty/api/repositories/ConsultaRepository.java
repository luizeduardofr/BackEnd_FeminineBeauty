package feminine_beauty.api.repositories;

import feminine_beauty.api.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByFuncionarioIdAndDataAndMotivoCancelamentoIsNull(Long idFuncionario, LocalDateTime data);

    boolean existsByClienteIdAndDataBetween(Long idCliente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
