package feminine_beauty.api.domain.consulta;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import feminine_beauty.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import feminine_beauty.api.domain.funcionario.Funcionario;
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

        if (dados.idFuncionario() != null && !funcionarioRepository.existsById(dados.idFuncionario())) {
            throw new ValidacaoException("Id do funcionario informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var funcionario = escolherFuncionario(dados);
        if (funcionario == null) {
            throw new ValidacaoException("Não existe funcionario disponível nesse data!");
        }

        var consulta = new Consulta(null, funcionario, cliente, dados.data(), null, null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Funcionario escolherFuncionario(DadosAgendamentoConsulta dados) {
        if (dados.idFuncionario() != null) {
            return funcionarioRepository.getReferenceById(dados.idFuncionario());
        }

        if (dados.servico() == null) {
            throw new ValidacaoException("Servico é obrigatório quando o médico não for escolhido!");
        }

        return funcionarioRepository.escolherMedicoAleatorioLivreNaData(dados.servico(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadorCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivoCancelamento());
    }
}
