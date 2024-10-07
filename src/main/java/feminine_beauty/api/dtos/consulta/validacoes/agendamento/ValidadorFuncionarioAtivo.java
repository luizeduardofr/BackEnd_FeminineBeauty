package feminine_beauty.api.dtos.consulta.validacoes.agendamento;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.dtos.consulta.DadosAgendamentoConsulta;
import feminine_beauty.api.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class ValidadorFuncionarioAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private FuncionarioRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        if (dados.funcionario().id() == null) {
            return;
        }

        var funcionarioEstaAtivo = repository.findAtivoById(dados.funcionario().id());
        if (!funcionarioEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com funcionário inativo.");
        }
    }
}
