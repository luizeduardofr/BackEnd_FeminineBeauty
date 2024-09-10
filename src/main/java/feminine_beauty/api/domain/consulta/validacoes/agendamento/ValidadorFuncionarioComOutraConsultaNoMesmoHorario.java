package feminine_beauty.api.domain.consulta.validacoes.agendamento;

import feminine_beauty.api.domain.consulta.DadosAgendamentoConsulta;
import feminine_beauty.api.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorFuncionarioComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        //var funcionarioPossuiOutraConsultaNoMesmoHorario = repository
    }
}
