package feminine_beauty.api.domain.consulta;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import feminine_beauty.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import feminine_beauty.api.repositories.ClienteRepository;
import feminine_beauty.api.repositories.ConsultaRepository;
import feminine_beauty.api.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadorCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {

        if (!clienteRepository.existsById(dados.idCliente())) {
            throw new ValidacaoException("Id do cliente informado nao existe!");
        }

    }
}
