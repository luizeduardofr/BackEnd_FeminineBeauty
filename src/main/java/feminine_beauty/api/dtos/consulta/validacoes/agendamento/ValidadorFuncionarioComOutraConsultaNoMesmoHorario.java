package feminine_beauty.api.dtos.consulta.validacoes.agendamento;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.dtos.consulta.DadosAgendamentoConsulta;
import feminine_beauty.api.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Component()
public class ValidadorFuncionarioComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {

        var funcionarioPossuiOutraConsultaNoMesmoHorario =
                repository.countByFuncionarioIdAndDataWithDuracao(dados.funcionario().id(), dados.data(),
                        dados.servico().duracao());
        if (funcionarioPossuiOutraConsultaNoMesmoHorario > 0) {
            throw new ValidacaoException("Funcionário já possui outra consulta agendada nesse mesmo horário.");
        }
    }
}
