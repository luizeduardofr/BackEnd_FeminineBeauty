package feminine_beauty.api.dtos.consulta.validacoes.agendamento;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.dtos.consulta.DadosAgendamentoConsulta;
import feminine_beauty.api.dtos.consulta.StatusConsulta;
import feminine_beauty.api.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class ValidadorClienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var clientePossuiOutraConsultaNoDia = repository.existsByClienteIdAndDataBetweenAndStatus(dados.cliente().id(), primeiroHorario, ultimoHorario, StatusConsulta.PENDENTE);
        if (clientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Cliente já possui uma consulta agendada nesse dia.");
        }
    }
}
