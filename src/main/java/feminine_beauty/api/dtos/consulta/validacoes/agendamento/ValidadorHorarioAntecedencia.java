package feminine_beauty.api.dtos.consulta.validacoes.agendamento;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.dtos.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Component()
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data().atZone(ZoneOffset.UTC);
        var dataTratada = dataConsulta.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataTratada).toMinutes();

        if (diferencaEmMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }
}
