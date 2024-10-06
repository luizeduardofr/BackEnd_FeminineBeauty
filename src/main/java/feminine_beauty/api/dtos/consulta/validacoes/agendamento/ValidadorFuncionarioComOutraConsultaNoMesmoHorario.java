package feminine_beauty.api.dtos.consulta.validacoes.agendamento;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.dtos.consulta.DadosAgendamentoConsulta;
import feminine_beauty.api.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorFuncionarioComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var funcionarioPossuiOutraConsultaNoMesmoHorario = repository.existsByFuncionarioIdAndDataAndMotivoCancelamentoIsNull(dados.funcionario().id(), dados.data());
        if (funcionarioPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Funcionário já possui outra consulta agendada nesse mesmo horário.");
        }
    }
}
