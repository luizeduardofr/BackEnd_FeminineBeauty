package feminine_beauty.api.domain.consulta.validacoes.agendamento;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.domain.consulta.DadosAgendamentoConsulta;
import feminine_beauty.api.domain.funcionario.Funcionario;
import feminine_beauty.api.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorFuncionarioAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private FuncionarioRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        if (dados.idFuncionario() == null) {
            return;
        }

        var funcionarioEstaAtivo = repository.findAtivoById(dados.idFuncionario());
        if (!funcionarioEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com funcionário inativo.");
        }
    }
}
