package feminine_beauty.api.dtos.consulta.validacoes.agendamento;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.dtos.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component()
public class ValidadorHorarioFuncionamentoSalao implements ValidadorAgendamentoDeConsulta{

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDoSalao = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDoSalao = dataConsulta.getHour() > 18;
        if(domingo || antesDaAberturaDoSalao || depoisDoEncerramentoDoSalao){
            throw new ValidacaoException("Consulta fora do horário de funcionamento do salão de beleza.");
        }
    }
}
