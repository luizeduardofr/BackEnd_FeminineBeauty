package feminine_beauty.api.dtos.consulta.validacoes.agendamento;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.dtos.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component()
public class ValidadorHorarioFuncionamentoSalao implements ValidadorAgendamentoDeConsulta{

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data().atZone(ZoneOffset.UTC);

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDoSalao = dataConsulta.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")).getHour() < 7;
        var depoisDoEncerramentoDoSalao = dataConsulta.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")).getHour() > 18;
        if(domingo || antesDaAberturaDoSalao || depoisDoEncerramentoDoSalao){
            throw new ValidacaoException("Consulta fora do horário de funcionamento do salão de beleza.");
        }
    }
}
