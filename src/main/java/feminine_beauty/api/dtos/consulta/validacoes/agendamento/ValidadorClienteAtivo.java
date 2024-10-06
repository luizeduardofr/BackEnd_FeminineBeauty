package feminine_beauty.api.dtos.consulta.validacoes.agendamento;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.dtos.consulta.DadosAgendamentoConsulta;
import feminine_beauty.api.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ClienteRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        var clienteEstaAtivo = repository.findAtivoById(dados.cliente().id());
        if (!clienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com cliente excluído.");
        }
    }
}
